package com.sty.sf.test;

import com.sty.sf.BeanDefinition;
import com.sty.sf.BeanFactory;
import com.sty.sf.test.bean.UserService;
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

        BeanFactory beanFactory = new BeanFactory();

        // 2. 注册bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.setBean("userService",beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
