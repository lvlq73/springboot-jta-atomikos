package com.llq.atomikos.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author lvlianqi
 * @description
 * @date 2022/3/7
 */
@Configuration
public class DBAtomikosConfig {

    //--------------------数据源1--------------------
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    @Bean
    public Properties testOneProperties() {
        return new Properties();
    }

    @Bean(name = "testOneDataSource")
    @Primary
    public DataSource testOneDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = testOneProperties();
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        ds.setUniqueResourceName("testOne");
        ds.setXaProperties(prop);
        return ds;
    }

    @Bean
    @Primary
    public JdbcTemplate testOneJdbcTemplate(@Qualifier("testOneDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    //--------------------数据源2--------------------
    @ConfigurationProperties(prefix = "spring.datasource.test2")
    @Bean
    public Properties testTwoProperties() {
        return new Properties();
    }

    @Bean(name = "testTwoDataSource")
    public DataSource testTwoDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        Properties prop = testTwoProperties();
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        ds.setUniqueResourceName("testTwo");
        ds.setXaProperties(prop);
        return ds;
    }

    @Bean
    public JdbcTemplate testTwoJdbcTemplate(@Qualifier("testTwoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    //--------------------配置spring的JtaTransactionManager，底层委派给atomikos进行处理--------------------
    @Bean
    public JtaTransactionManager jtaTransactionManager () {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
