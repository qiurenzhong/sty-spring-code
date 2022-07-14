package com.sty.sf.beans.common;

import com.sty.sf.beans.factory.BeansException;
import com.sty.sf.beans.factory.PropertyValue;
import com.sty.sf.beans.factory.PropertyValues;
import com.sty.sf.beans.factory.ConfigurableListableBeanFactory;
import com.sty.sf.beans.factory.config.BeanDefinition;
import com.sty.sf.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
