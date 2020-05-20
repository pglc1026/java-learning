/**    
 * <p> Copyright (c) 2015-2025 微聚未来</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */

package jl.mybatis.mapper;


import jl.mybatis.po.MybatisUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Ge Hui
 */
@Mapper
public interface MybatisUserMapper {

    /**
     * 插入一行数据，如果字段为null，则不处理
     *
     * @param mybatisUser mybatisUser
     * @return count
     */
    int saveSelective(MybatisUser mybatisUser);

    /**
     * 根据主键更新一行数据，如果字段为null，则不处理
     *
     * @param mybatisUser mybatisUser
     * @return count
     */
    int updateByPrimaryKeySelective(MybatisUser mybatisUser);

    /**
     * 根据主键查询一条数据
     *
     * @param id id
     * @return po
     */
    MybatisUser getByPrimaryKey(Long id);


}