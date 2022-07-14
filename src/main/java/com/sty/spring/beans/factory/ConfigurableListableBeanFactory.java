package com.sty.spring.beans.factory;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.sty.spring.beans.factory.config.BeanDefinition;
import com.sty.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * 可配置的列表Bean工厂
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    void preInstantiateSingletons() throws BeansException;

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
