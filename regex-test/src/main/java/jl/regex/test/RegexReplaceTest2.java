package jl.regex.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexReplaceTest
 *
 * @author Liu Chang
 * @date 2020/3/31
 */
public class RegexReplaceTest2 {

    public static void main(String[] args) {
        String text1 = "\"realName\":\"李梦媛\"\n" +
                "\"bindNo\":\"4651390298805618828\"\n" +
                "\"cardNo\":\"6217002280012759288\"";

        Pattern pattern = Pattern.compile("\"(cardNo|bindNo|realName)\"\\s*:\\s*\"(.+)\"");

        Matcher matcher = pattern.matcher(text1);
        while (matcher.find()) {
//            System.out.println(text1);

//            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(2));
            text1 = text1.replace(matcher.group(2), "***");
        }

        System.out.println(text1);

    }

}
