package jl.jsql.parser.mytest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.util.SelectUtils;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * MyTest
 *
 * @author Liu Chang
 * @date 2020/5/22
 */
public class MyTest {

    private static final Gson GSON = new GsonBuilder().create();

    private static final String TABLE_NAME = "fund_loan_sign_record";

    private static final Integer TOTAL = 27153282;

    private static final Long STEP = 1000000L;

    @SneakyThrows
    public static void main(String[] args) {
        // 获取配置
        Map<String, String> columnMap = Maps.newLinkedHashMap();
        JsonObject jsonObject = GSON.fromJson(
                new FileReader(Objects.requireNonNull(MyTest.class.getClassLoader().getResource("")).getPath() + "encrypt_jdbc.conf"),
                JsonObject.class);
        JsonArray tables = jsonObject.getAsJsonArray("tables");
        for (JsonElement ele : tables) {
            JsonObject table = (JsonObject) ele;
            if (TABLE_NAME.equals(table.get("tableName").getAsString())) {
                JsonArray columns = table.getAsJsonArray("columns");
                for (JsonElement colEle : columns) {
                    JsonObject col = (JsonObject) colEle;
                    columnMap.put(col.get("plainColumn").getAsString(), col.get("cipherColumn").getAsString());
                }
            }
        }

        // 组装SQL
        Select select = SelectUtils.buildSelectFromTableAndExpressions(new Table(TABLE_NAME), "ID");
//        System.out.println("1: " + select);
        // 组装条件 ((plainColumn1 IS NOT NULL AND plainColumn1 != '') AND (cipherColumn1 IS NULL OR cipherColumn1 = ''))
        //          OR ((plainColumn2 IS NOT NULL AND plainColumn2 != '') AND (cipherColumn2 IS NULL OR cipherColumn2 = ''))


        List<Parenthesis> condList = Lists.newArrayList();
        for (String plainColumn : columnMap.keySet()) {
            String cipherColumn = columnMap.get(plainColumn);
            // 1. (plainColumn1 IS NOT NULL AND plainColumn1 != '')
            // 1.1 plainColumn1 IS NOT NULL
            // 1.1.1 plainColumn1
            Column plainColExp = new Column();
            plainColExp.setColumnName(plainColumn);
            // 1.1.2 IS NOT NULL
            IsNullExpression plainIsNotNullExp = new IsNullExpression();
            plainIsNotNullExp.setNot(true);
            plainIsNotNullExp.setLeftExpression(plainColExp);
            // 1.2 plainColumn1 != ''
            NotEqualsTo plianNotEqualsTo = new NotEqualsTo();
            plianNotEqualsTo.setLeftExpression(plainColExp);
            plianNotEqualsTo.setRightExpression(new StringValue(""));
            // 1.3 (...)
            Parenthesis plainParenthesis = new Parenthesis();
            plainParenthesis.setExpression(new AndExpression(plainIsNotNullExp, plianNotEqualsTo));

            // 2. (cipherColumn1 IS NULL OR cipherColumn1 = '')
            // 2.1.1 cipherColumn1
            Column cipherColExp = new Column();
            cipherColExp.setColumnName(cipherColumn);
            // 2.1.2 IS NULL
            IsNullExpression cipherIsNullExp = new IsNullExpression();
            cipherIsNullExp.setLeftExpression(cipherColExp);
            cipherIsNullExp.setNot(false);
            // 2.2 cipherColumn = ''
            EqualsTo cipherEqualsTo = new EqualsTo();
            cipherEqualsTo.setLeftExpression(cipherColExp);
            cipherEqualsTo.setRightExpression(new StringValue(""));
            // 2.3 (..)
            Parenthesis cipherParenthesis = new Parenthesis();
            cipherParenthesis.setExpression(new OrExpression(cipherIsNullExp, cipherEqualsTo));

            // 3. (() AND ())
            Parenthesis condParenthesis = new Parenthesis();
            condParenthesis.setExpression(new AndExpression(plainParenthesis, cipherParenthesis));

            condList.add(condParenthesis);
        }

        SelectBody selectBody = select.getSelectBody();
        PlainSelect plainSelectBody = (PlainSelect) selectBody;

        if (condList.size() >= 2) {
            // 拼接or
            OrExpression orExpression = new OrExpression(condList.get(0), condList.get(1));
            for (int i = 2; i < condList.size(); i++) {
                orExpression = new OrExpression(orExpression, condList.get(i));
            }
            // 拼个括号
            Parenthesis parenthesisAll = new Parenthesis();
            parenthesisAll.setExpression(orExpression);
            plainSelectBody.setWhere(parenthesisAll);
        } else {
            Parenthesis parenthesisAll = new Parenthesis();
            parenthesisAll.setExpression(condList.get(0));
            plainSelectBody.setWhere(parenthesisAll);
        }

        // 拼接ID条件
        Column idCol = new Column();
        idCol.setColumnName("ID");
        for (int i = 0; i < TOTAL + STEP; i += STEP) {
            // ... AND (ID BETWEEN i AND i + STEP)
            Between between = new Between();
            between.setLeftExpression(idCol);
            between.setBetweenExpressionStart(new LongValue(i));
            between.setBetweenExpressionEnd(new LongValue(i + STEP));
            Parenthesis betweenParenthesis = new Parenthesis();
            betweenParenthesis.setExpression(between);
            Expression whereExp = plainSelectBody.getWhere();
            if (whereExp instanceof Parenthesis) {
                // 拼接AND
                plainSelectBody.setWhere(new AndExpression(whereExp, betweenParenthesis));
            } else {
                // 修改between部分
                ((AndExpression) whereExp).setRightExpression(betweenParenthesis);
            }

            System.out.print(select);
            System.out.print(";");
            System.out.println();
        }


//        System.out.println("2: " + select);
    }

}
