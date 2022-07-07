package com.sty.sf.beans;

import com.sty.sf.beans.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/7
 * @version 1.0.0
 */
public class ApiTest {

    @Test
    public void test_createContext(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

        // 2.获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.queryUserInfo();
        System.out.println(userInfo);

    }


}
