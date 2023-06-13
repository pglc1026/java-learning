package com.liuchang.easyexcel.complexheader1.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Will Liu
 * @since 2023/1/29
 */

@Data
public class Detail {
    private String productId;
    private String productName;
    private BigDecimal price;
    private BigDecimal number;
    private BigDecimal amount;
    private String memo;
}