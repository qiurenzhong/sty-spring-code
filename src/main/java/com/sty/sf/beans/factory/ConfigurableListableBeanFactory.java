package com.sty.sf.beans.factory;

import com.sty.sf.beans.factory.config.AutowireCapableBeanFactory;
import com.sty.sf.beans.factory.config.BeanDefinition;
import com.sty.sf.beans.factory.config.ConfigurableBeanFactory;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    void preInstantiateSingletons() throws BeansException;

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
