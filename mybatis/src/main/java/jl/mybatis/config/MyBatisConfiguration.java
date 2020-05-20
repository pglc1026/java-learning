package jl.mybatis.config;

import jl.mybatis.type.alias.AESEncrypt;
import jl.mybatis.type.handler.EncryptTypeHandler;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * MyBatisConfiguration
 *
 * @author Liu Chang
 * @date 2020/5/20
 */
@Configuration
@MapperScan(basePackages = "jl.mybatis.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfiguration {

    public static final String MAPPER_LOCATION = "classpath*:sqlmap/**/*SqlMap.xml";

    public static final String TYPE_ALIASES_PACKAGE = "";

    public static final String TYPE_HANDLERS_PACKAGE = "";

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory
                = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 注册TypeAlias和TypeAliasHandler
        sessionFactory.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sessionFactory.setTypeHandlersPackage(TYPE_HANDLERS_PACKAGE);
        PathMatchingResourcePatternResolver resourcePatternResolver
                = new PathMatchingResourcePatternResolver();
        sessionFactory
                .setMapperLocations(resourcePatternResolver
                        .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
