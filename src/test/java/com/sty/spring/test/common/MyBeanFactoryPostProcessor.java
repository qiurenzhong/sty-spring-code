package com.sty.spring.test.common;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.PropertyValue;
import com.sty.spring.beans.PropertyValues;
import com.sty.spring.beans.factory.ConfigurableListableBeanFactory;
import com.sty.spring.beans.factory.config.BeanDefinition;
import com.sty.spring.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
