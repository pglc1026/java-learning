package com.liuchang.easyexcel.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Will Liu
 * @date 2022/4/13
 */
@Data
public class StudentInfo {

    @ExcelProperty("学生姓名（必填）")
    private String studentName;

    @ExcelProperty("家长的手机号码（必填）")
    private String parentMobileNumber;

    @ExcelProperty("学生的身份证号（必填）")
    private String studentIdCard;

    @ExcelProperty("共同居住者1的姓名、与您的关系、年龄、联系电话、身份证号，工作单位或就读学校（必填）")
    private String personInfo1;

    @ExcelProperty("共同居住者2的姓名、与您的关系、年龄、联系电话、身份证号、工作单位或就读学校（必填）")
    private String personInfo2;

    @ExcelProperty("共同居住者3的姓名、与您的关系、年龄、联系电话、身份证号、工作单位或就读学校")
    private String personInfo3;

    @ExcelProperty("共同居住者4的姓名、与您的关系、年龄、联系电话、身份证号、工作单位或就读学校")
    private String personInfo4;

    @ExcelProperty("共同居住者5的姓名、与您的关系、年龄、联系电话、身份证号、工作单位或就读学校")
    private String personInfo5;

    @ExcelProperty("其他需要说明的问题")
    private String personInfo6;

}
