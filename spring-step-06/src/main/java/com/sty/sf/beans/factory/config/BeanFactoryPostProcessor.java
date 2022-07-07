package com.sty.sf.beans.factory.config;

import com.sty.sf.beans.BeansException;
import com.sty.sf.beans.factory.ConfigurableListableBeanFactory;

/**
 * 提供修改 BeanDefinition 属性的机制
 *
 * @author tianma
 * @date 2022/6/30
 * @version 1.0.0
 */
public interface BeanFactoryPostProcessor {

    /**
     *  在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
