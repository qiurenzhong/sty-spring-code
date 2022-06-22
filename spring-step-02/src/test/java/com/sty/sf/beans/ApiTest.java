package com.sty.sf.beans;

import com.sty.sf.beans.bean.UserService;
import com.sty.sf.beans.factory.config.BeanDefinition;
import com.sty.sf.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {

        // 1. 初始化BeanFactory

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        System.out.println(userService);

        // 4. 第二次获取bean
        UserService userService2 = (UserService) beanFactory.getBean("userService2");
        userService2.queryUserInfo();
        System.out.println(userService2);
    }
}
