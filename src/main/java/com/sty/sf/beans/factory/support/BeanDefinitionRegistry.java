package com.sty.sf.beans.factory.support;

import com.sty.sf.beans.factory.config.BeanDefinition;

/**
 * 注册bean
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public interface BeanDefinitionRegistry {

    /**
     *  向注册表中注册 BeanDefinition
     * @param beanName bean名称
     * @param beanDefinition 对象
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
