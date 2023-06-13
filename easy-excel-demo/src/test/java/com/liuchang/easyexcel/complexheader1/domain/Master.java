package com.liuchang.easyexcel.complexheader1.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Will Liu
 * @since 2023/1/29
 */
@Data
@ToString
public class Master {
    private String sheetNo;
    private String customer;
    private Date createdDate;
}