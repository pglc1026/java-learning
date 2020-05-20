package jl.mybatis.type.handler;


import jl.mybatis.type.alias.AESEncrypt;
import jl.mybatis.util.EndecryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * EncryptTypeHandler
 *
 * @author Liu Chang
 * @date 2020/5/20
 */
@MappedTypes(AESEncrypt.class)
@Slf4j
public class EncryptTypeHandler extends BaseTypeHandler<String> {

    /**
     * 用于定义在Mybatis设置参数时该如何把Java类型的参数转换为对应的数据库类型
     *
     * @param ps        当前的PreparedStatement对象
     * @param i         当前参数的位置
     * @param parameter 当前参数的Java对象
     * @param jdbcType  当前参数的数据库类型
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i
            , String parameter, JdbcType jdbcType)
            throws SQLException {
        // 只要 parameter 非空都进行加密
        log.info("setNonNullParameter index <{}>, param <{}> ", i, parameter);
        ps.setString(i, EndecryptUtil.encrypt(parameter));
    }

    /**
     * 用于在Mybatis获取数据结果集时如何把数据库类型转换为对应的Java类型
     *
     * @param rs         当前的结果集
     * @param columnName 当前的字段名称
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet rs
            , String columnName) throws SQLException {
        String r = rs.getString(columnName);
        return r == null ? null : EndecryptUtil.decrypt(r);
    }

    /**
     * 用于在Mybatis通过字段位置获取字段数据时把数据库类型转换为对应的Java类型
     *
     * @param rs          当前的结果集
     * @param columnIndex 当前字段的位置
     * @return 转换后的Java对象
     * @throws SQLException
     */
    @Override
    public String getNullableResult(ResultSet rs
            , int columnIndex) throws SQLException {
        String r = rs.getString(columnIndex);
        return r == null ? null : EndecryptUtil.decrypt(r);
    }

    /**
     * 用于Mybatis在调用存储过程后把数据库类型的数据转换为对应的Java类型
     *
     * @param cs          当前的CallableStatement执行后的CallableStatement
     * @param columnIndex 当前输出参数的位置
     * @return
     * @throws SQLException
     */
    @Override
    public String getNullableResult(CallableStatement cs
            , int columnIndex) throws SQLException {
        String r = cs.getString(columnIndex);
        // 兼容待修复的数据
        return r == null ? null : EndecryptUtil.decrypt(r);
    }
}
