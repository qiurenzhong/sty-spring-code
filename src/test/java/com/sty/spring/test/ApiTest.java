package com.sty.spring.test;

import com.sty.spring.context.support.ClassPathXmlApplicationContext;
import com.sty.spring.test.dao.UserService;
import org.junit.Test;

/**
 *  单元测试类
 *
 * @author tianma
 * @date 2022/7/7
 * @version 1.0.0
 */
public class ApiTest {

    @Test
    public void test_createContext(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");

        // 2.获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.queryUserInfo();
        System.out.println(userInfo);

    }

    @Test
    public void test_initOrDestroy(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");

        // 注册钩子
        applicationContext.registerShutdownHook();

        // 2.获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.queryUserInfo();
        System.out.println(userInfo);

    }
}
