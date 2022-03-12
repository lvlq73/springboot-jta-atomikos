package com.llq.atomikos.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * @author lvlianqi
 * @description
 * @date 2022/3/7
 */
@Configuration
public class DBConfig {

    //--------------------数据源1--------------------
    @Bean(name = "testOneNormalDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.normal.test1")
    public DataSource testOneNormalDataSource() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    @Primary
    public JdbcTemplate testOneNormalJdbcTemplate(@Qualifier("testOneNormalDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManagerOne(@Qualifier("testOneNormalDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //--------------------数据源2--------------------
    @Bean(name = "testTwoNormalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.normal.test2")
    public DataSource testTwoNormalDataSource() {
        DataSource ds = DataSourceBuilder.create().build();
        return ds;
    }

    @Bean
    public JdbcTemplate testTwoNormalJdbcTemplate(@Qualifier("testTwoNormalDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManagerTwo(@Qualifier("testTwoNormalDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
