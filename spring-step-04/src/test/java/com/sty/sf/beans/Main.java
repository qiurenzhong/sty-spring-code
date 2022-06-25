package com.sty.sf.beans;

import com.sty.sf.beans.factory.config.BeanDefinition;
import com.sty.sf.beans.factory.config.BeanReference;
import com.sty.sf.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/25
 * @version 1.0.0
 */
public class Main {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "100002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
