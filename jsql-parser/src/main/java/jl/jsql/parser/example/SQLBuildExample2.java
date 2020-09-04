package jl.jsql.parser.example;

import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

/**
 * SQLBuildExample2
 *
 * @author Liu Chang
 * @date 2020/5/21
 */
public class SQLBuildExample2 {

    @SneakyThrows
    public static void main(String[] args) {
        Select stmt = (Select) CCJSqlParserUtil.parse("SELECT col1 AS a, col2 AS b, col3 AS c FROM table WHERE col_1 = 10 AND (col_2 = 20 AND col_3 != 30)");
        System.out.println("before " + stmt.toString());
        String name = ((Parenthesis) ((BinaryExpression) ((PlainSelect) stmt.getSelectBody()).getWhere()).getRightExpression()).getExpression().getClass().getName();
        System.out.println(name);


        ((PlainSelect)stmt.getSelectBody()).getWhere().accept(new ExpressionVisitorAdapter() {
            @Override
            public void visit(Column column) {
                column.setColumnName(column.getColumnName().replace("_", ""));
            }
        });

        System.out.println("after " + stmt.toString());
    }

}
