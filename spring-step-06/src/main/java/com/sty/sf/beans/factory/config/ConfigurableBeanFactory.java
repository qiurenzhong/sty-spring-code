package com.sty.sf.beans.factory.config;

import com.sty.sf.beans.factory.HierarchicalBeanFactory;

/**
 * TODO 一句话描述
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
