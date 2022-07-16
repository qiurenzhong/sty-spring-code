package com.sty.spring.beans.factory.config;

import com.sty.spring.beans.BeansException;

/**
 * bean初始化前后置处理器
 *
 * @author tianma
 * @date 2022/6/30
 * @version 1.0.0
 */
public interface BeanPostProcessor {

    /**
     *  在 Bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     *  在 Bean 对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
