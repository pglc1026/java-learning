package jl.jsql.parser.mytest;

import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;

import java.util.Objects;

/**
 * SQLBuildExample2
 *
 * @author Liu Chang
 * @date 2020/5/21
 */
public class SQLBuildTest2 {

    @SneakyThrows
    public static void main(String[] args) {
        String sql1 = "SELECT col1 AS a, col2 AS b, col3 AS c FROM table WHERE col_1 = 10 AND col_2 = 20 AND col_3 = 30 LIMIT 500";
        String sql2 = "SELECT MAX(ID) FROM table";
        Select stmt = (Select) CCJSqlParserUtil
                .parse(sql2);
        System.out.println("before " + stmt.toString());
        // 替换列名
        ((PlainSelect) stmt.getSelectBody()).getSelectItems().forEach(selectItem -> {
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {
                    Expression expression = item.getExpression();
                    if (expression instanceof Function) {
                        Function func = (Function) expression;
                        System.out.println(func.getAttribute());
                    }
                    System.out.println(expression.getClass().getName());
                    if (expression instanceof Column) {
                        Column column = (Column) expression;
                        column.setColumnName("`" + column.getColumnName() + "`");
                    }
                }
            });
        });

        Limit limit = ((PlainSelect) stmt.getSelectBody()).getLimit();
//        System.out.println(limit);
        if (Objects.nonNull(limit)) {
            boolean limitNull = limit.isLimitNull();
            System.out.println(limit.getOffset());
            System.out.println(limit.getRowCount());
            System.out.println(limit.getRowCount().getClass().getName());
            limit.setOffset(null);
            limit.getRowCount().accept(new ExpressionVisitorAdapter() {
                @Override
                public void visit(LongValue value) {
                    value.setValue(10);
                }
            });
            System.out.println(limitNull);
            System.out.println(limit.isLimitAll());
        }


//        ((PlainSelect) stmt.getSelectBody()).getSelectItems().forEach(selectItem -> {
//            selectItem.accept(new SelectItemVisitorAdapter() {
//                @Override
//                public void visit(SelectExpressionItem item) {
//                    SimpleNode node = item.getExpression().getASTNode();
//                    System.out.println(node.jjtGetValue());
//                    System.out.println(item.getExpression());
//                }
//            });
//        });

//                ((PlainSelect)stmt.getSelectBody()).getWhere().accept(new ExpressionVisitorAdapter() {
//                    @Override
//                    public void visit(Column column) {
//                        column.setColumnName(column.getColumnName().replace("_", ""));
//                    }
//                });
//
        System.out.println("after " + stmt.toString());
    }

}
