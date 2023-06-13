package com.liuchang.easyexcel.complexheader2.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Will Liu
 * @since 2023/1/29
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WriteData {

    @ExcelProperty("字段1")
    private String filed1;

    @ExcelProperty("字段2")
    private String field2;

    @ExcelProperty("字段3")
    private String field3;

    @ExcelProperty("字段4")
    @ColumnWidth(50)
    private String field4;

    @ExcelProperty("字段5")
    @ColumnWidth(50)
    private String filed5;

}
