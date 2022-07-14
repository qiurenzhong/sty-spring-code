package com.sty.spring.beans.factory.config;


import com.sty.spring.beans.factory.HierarchicalBeanFactory;

/**
 * 可配置的Bean工厂
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
