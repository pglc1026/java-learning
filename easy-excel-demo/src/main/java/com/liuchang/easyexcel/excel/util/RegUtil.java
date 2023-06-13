package com.liuchang.easyexcel.excel.util;

import com.liuchang.easyexcel.excel.domain.RegResult;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Will Liu
 * @date 2022/4/13
 */
public class RegUtil {

    private static final String REG_0 = "(([\\u4e00-\\u9fa5]{0,4})[\\s，,“‘’]+)?(([\\u4e00-\\u9fa5]*)[\\s，,“‘’]+)?((\\d{1,3}[岁]?)?[\\s，,“‘’]+)?([\\u4e00-\\u9fa5]*[\\s，,“‘’]+)?((\\d{7,11})?[\\s，,“‘’]+)?([身份证号]*[\\s，,“‘’]+)?((\\d{15,})?[\\s，,“‘’]+)?([工作单位]*[\\s，,“‘’]+)?([\\u4e00-\\u9fa5]*)";
    private static final String REG_1 = "([\\u4e00-\\u9fa5]{0,4})[\\s，,“‘’]*([\\u4e00-\\u9fa5]*)[\\s，,“‘’]*(\\d{1,3}[岁]?)?[\\s，,“‘’]*[\\u4e00-\\u9fa5]*[\\s，,“‘’]*(\\d{7,11})?[\\s，,“‘’]*[身份证号]*[\\s，,“‘’]*(\\d{15,})?[\\s，,“‘’]*[工作单位]*[\\s，,“‘’]*([\\u4e00-\\u9fa5]*)";
    private static final Pattern PATTERN_0 = Pattern.compile(REG_0);
    private static final Pattern PATTERN_1 = Pattern.compile(REG_1);
    
    private static final Pattern PATTERN_2 = Pattern.compile("([\\u4e00-\\u9fa5]{0,4}).*");

    private static final Pattern PATTERN_3 = Pattern.compile(".*?(\\d{17}[\\dxX]?).*");

    private static final String NO = "无没有";

    public static Optional<RegResult> getResult(String text, String studentName) {
        if (StringUtils.isBlank(text) || NO.contains(text) || studentName.equals(text)) {
            return Optional.empty();
        }
        Matcher matcher = PATTERN_2.matcher(text);
        String name = "";
        if (matcher.matches()) {
            name = matcher.group(1);
        }
        String idCard = "";
        Matcher matcher1 = PATTERN_3.matcher(text);
        if (matcher1.matches()) {
            idCard = matcher1.group(1);
        }
        if (StringUtils.isNoneBlank(name) || StringUtils.isNoneBlank(idCard)) {
            return Optional.of(new RegResult(name, idCard));
        }
        return Optional.empty();
    }

    public static Optional<RegResult> getDetailResult(String text, String studentName) {
        if (StringUtils.isBlank(text) || NO.contains(text) || studentName.equals(text)) {
            return Optional.empty();
        }
        Matcher matcher = PATTERN_0.matcher(text);
        System.out.printf("表达式 1 匹配结果: %s\n", matcher.matches());
        if (matcher.matches()) {
            try {
                return Optional.of(new RegResult(matcher.group(2), matcher.group(12)));
            } catch (Exception e) {
                System.err.println(text);
                throw e;
            }
        } else {
            Matcher matcher1 = PATTERN_1.matcher(text);
            System.out.printf("表达式 2 匹配结果: %s\n", matcher1.matches());
            if (matcher1.matches()) {
                try {
                    return Optional.of(new RegResult(matcher1.group(1), matcher1.group(5)));
                } catch (Exception e) {
                    System.err.println(text);
                    throw e;
                }
            }
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
//        String text = "施文花 母亲       42岁\n电话 18678880098\n身份证号370112198110062546\n单位    无";
//        String text = "张旭 ,爷爷 , 69岁,13963764479，370823195309270012 退休";
//        String text = "赵振兴  姥爷  80  612421194212281168   退休";
//        String text = "王一凡，妹妹，18663739061，经十一路小学";
//        String text = "陈溪儒，妹妹，5岁，银座百花园370112201607250301";
        String text = "张淑钰，2岁8个月。370103201907230162";
        Matcher matcher2 = PATTERN_2.matcher(text);
        matcher2.matches();
        System.out.println(matcher2.group(1));
//        Matcher matcher = PATTERN_0.matcher(text);
//        System.out.printf("表达式 1 匹配结果: %s\n", matcher.matches());
//        if (matcher.matches()) {
//            System.out.printf("匹配组数: %d\n", matcher.groupCount());
//            for (int i = 1; i <= matcher.groupCount(); i++) {
//                System.out.printf("第 %d 组: %s\n", i, Optional.ofNullable(matcher.group(i)).orElse("无"));
//            }
//        } else {
//            Matcher matcher1 = PATTERN_1.matcher(text);
//            System.out.printf("表达式 2 匹配结果: %s\n", matcher1.matches());
//            if (matcher1.matches()) {
//                System.out.printf("匹配组数: %d\n", matcher1.groupCount());
//                for (int i = 1; i <= matcher1.groupCount(); i++) {
//                    System.out.printf("第 %d 组: %s\n", i, Optional.ofNullable(matcher1.group(i)).orElse("无"));
//                }
//            }
//        }
    }

}
