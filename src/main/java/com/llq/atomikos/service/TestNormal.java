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
public class TestNormal implements ITest{

    @Qualifier("testOneNormalJdbcTemplate")
    @Autowired
    private JdbcTemplate testOneNormalJdbcTemplate;
    @Qualifier("testTwoNormalJdbcTemplate")
    @Autowired
    private JdbcTemplate testTwoNormalJdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        testOneNormalJdbcTemplate.execute("insert into user (name, age) values ('张三', 18);");
        testTwoNormalJdbcTemplate.execute("insert into user (name, age) values ('李四', 20);");
    }

    /**
     * 测试异常情况
     */
    @Transactional(rollbackFor = Exception.class)
    public void testByException() {
        testOneNormalJdbcTemplate.execute("insert into user (name, age) values ('张三', 18);");
        testTwoNormalJdbcTemplate.execute("insert into user (name, age) values ('李四', 20);");
        int i = 1/0;
    }
}
