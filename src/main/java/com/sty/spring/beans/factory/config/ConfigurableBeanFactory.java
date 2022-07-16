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

    /**
     *  bean作用域：单例
     */
    String SCOPE_SINGLETON = "singleton";
    /**
     *  bean作用域：多例
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     *  添加bean初始化前后置处理器
     * @param beanPostProcessor 前后置处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     *  销毁单例bean
     */
    void destroySingletons();
}
