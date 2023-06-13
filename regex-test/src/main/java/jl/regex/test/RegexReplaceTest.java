package jl.regex.test;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexReplaceTest
 *
 * @author Liu Chang
 * @date 2020/3/31
 */
public class RegexReplaceTest {

    public static void main(String[] args) {
        String text1 = "\"realName\": \"李梦媛\"";
        String text2 = "\"bindNo\":\"4651390298805618828\"";
        String text3 = "\"cardNo\":\"6217002280012759288\"";

        Pattern pattern = Pattern.compile("\"(cardNo|bindNo|realName)\"\\s*:\\s*\"(.+)\"");
        Matcher matcher = pattern.matcher(text1);
        if (matcher.find()) {
            System.out.println(matcher.group(2));
            System.out.println(text1.replace(matcher.group(2), "***"));
        }
        System.out.println("=============================================");
        Matcher matcher2 = pattern.matcher(text2);
        if (matcher2.find()) {
            System.out.println(matcher2.group(2));
            System.out.println(text2.replace(matcher2.group(2), "***"));
        }
        System.out.println("=============================================");
        Matcher matcher3 = pattern.matcher(text3);
        if (matcher3.find()) {
            System.out.println(matcher3.group(2));
            System.out.println(text3.replace(matcher3.group(2), "***"));
        }
    }

}
