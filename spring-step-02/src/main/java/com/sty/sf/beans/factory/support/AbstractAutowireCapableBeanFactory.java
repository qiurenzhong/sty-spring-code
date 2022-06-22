package com.sty.sf.beans.factory.support;

import com.sty.sf.beans.BeansException;
import com.sty.sf.beans.factory.config.BeanDefinition;

/**
 * 创建bean
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {

        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);
        return bean;
    }
}
