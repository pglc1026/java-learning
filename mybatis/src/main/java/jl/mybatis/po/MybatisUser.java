/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package jl.mybatis.po;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ge Hui
 */
@Data
public class MybatisUser {

    /** id */
    private Long id;

    /** mobile */
    private String mobile;

    /** idCard */
    private String idCard;

}
