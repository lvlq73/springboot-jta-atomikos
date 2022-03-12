package com.llq.atomikos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lvlianqi
 * @description
 * @date 2022/3/7
 */
@Service
public class TestAtomikos implements ITest{

    @Qualifier("testOneJdbcTemplate")
    @Autowired
    private JdbcTemplate testOneJdbcTemplate;

    @Qualifier("testTwoJdbcTemplate")
    @Autowired
    private JdbcTemplate testTwoJdbcTemplate;

    /**
     * 测试正常情况
     */
    @Transactional(rollbackFor = Exception.class, value = "jtaTransactionManager")
    public void test() {
        testOneJdbcTemplate.execute("insert into user (name, age) values ('张三', 18);");
        testTwoJdbcTemplate.execute("insert into user (name, age) values ('李四', 20);");
    }

    /**
     * 测试异常情况
     */
    @Transactional(rollbackFor = Exception.class, value = "jtaTransactionManager")
    public void testByException() {
        testOneJdbcTemplate.execute("insert into user (name, age) values ('张三', 18);");
        testTwoJdbcTemplate.execute("insert into user (name, age) values ('李四', 20);");
        int i = 1/0;
    }
}
