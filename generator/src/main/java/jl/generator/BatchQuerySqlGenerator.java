package jl.generator;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
/**
 * BatchQuerySqlGenerator
 *
 * @author Liu Chang
 * @date 2020/4/16
 */
public class BatchQuerySqlGenerator {


    public void generateSql(long begin, long end, int step, String tableName, List<String> columns) {
        String sqlFormat = "SELECT `ID` FROM `%s` WHERE (`ID` > %d AND `ID` < %d) AND (%s)";
        String singleConditionFormat = "((`%s` IS NOT NULL AND `%s` != '') AND `%s` IS NULL)";
        String cipherNameFormatUpper = "%s_CIPHER";
        String cipherNameFormatLower = "%_cipher";

        // process columns to generate condition part
        String conditionPart = columns.stream().map(column -> {
            String cipherFormat = Character.isUpperCase(column.charAt(0)) ? cipherNameFormatUpper : cipherNameFormatLower;
            String singleColumnCondition = format(singleConditionFormat, column, column, format(cipherFormat, column));
            return singleColumnCondition;
        }).collect(Collectors.joining(" OR "));

        // generate sql
        List<String> sqlList = Lists.newArrayList();
        long tempBegin = begin;
        long tempEnd;
        while (tempBegin < end) {
            tempEnd = tempBegin + step;
            sqlList.add(format(sqlFormat, tableName, tempBegin, tempEnd - 1, conditionPart));
            tempBegin += step;
        }

        String batchFormat = "(%s) t%d";
        String batchSqlFormat = "SELECT %s FROM %s";
        String selectPartFormat = "%s, t%d.`ID`";
        String selectPart = "t0.`ID`";
        sqlList.set(0, format(batchFormat, sqlList.get(0), 0));
        for (int i = 1; i < sqlList.size(); i++) {
            selectPart = format(selectPartFormat, selectPart, i);
            sqlList.set(i, format(batchFormat, sqlList.get(i), i));
        }
        String finalSql = format(batchSqlFormat, selectPart, String.join(", ", sqlList));
        System.out.println(finalSql);
    }

    public static void main(String[] args) {
        String tableName = "fund_loan_order_sub";
        List<String> columns = Lists.newArrayList("REAL_NAME", "ID_CARD", "BANK_BIND_MOBILE", "REMARK", "REL_NAME", "REL_MOBILE");
        new BatchQuerySqlGenerator().generateSql(0, 10580548L, 200000, tableName, columns);
    }

}
