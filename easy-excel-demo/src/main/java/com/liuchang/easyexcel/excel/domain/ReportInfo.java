package com.liuchang.easyexcel.excel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import lombok.Data;

/**
 * @author Will Liu
 * @date 2022/4/13
 */
@Data
public class ReportInfo {

    @ExcelProperty("身份类型")
    @ColumnWidth(13)
    @ContentFontStyle(fontName = "微软雅黑")
    private String type = "学生";

    @ExcelProperty("师生姓名")
    @ColumnWidth(13)
    @ContentFontStyle(fontName = "微软雅黑")
    private String name;

    @ExcelProperty("师生证件号码")
    @ColumnWidth(25)
    @ContentFontStyle(fontName = "微软雅黑")
    private String idCard;

    @ExcelProperty("共同居住人姓名")
    @ColumnWidth(13)
    @ContentFontStyle(fontName = "微软雅黑")
    private String personName;

    @ExcelProperty("共同居住人证件类型")
    @ColumnWidth(15)
    @ContentFontStyle(fontName = "微软雅黑")
    private String idType = "居民身份证";

    @ExcelProperty("共同居住人证件号码")
    @ColumnWidth(25)
    @ContentFontStyle(fontName = "微软雅黑")
    private String personIdCard;

    public ReportInfo(String name, String idCard, String personName, String personIdCard) {
        this.name = name;
        this.idCard = idCard;
        this.personName = personName;
        this.personIdCard = personIdCard;
    }
}
