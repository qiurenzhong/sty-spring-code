package com.sty.sf.beans.factory.config;

/**
 * BeanDefinition 定义
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class BeanDefinition {

    private final Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass){
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

}
