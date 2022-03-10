package com.llq.atomikos;

import com.llq.atomikos.service.ITest;
import com.llq.atomikos.service.TestAtomikos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringbootAtomikosApplicationTests {

    //使用atomikos
    private static Class CLS = TestAtomikos.class;
    //正常情况
//    private static Class CLS = TestNormal.class;

    @Autowired
    ApplicationContext applicationContext;



    @Test
    public void test() {
        ITest test = (ITest) applicationContext.getBean(CLS);
        test.test();
    }

    @Test
    public void testByException() {
        ITest test = (ITest) applicationContext.getBean(CLS);
        test.testByException();
    }

}
