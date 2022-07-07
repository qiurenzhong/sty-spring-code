package com.sty.sf.beans.factory.config;

import com.sty.sf.beans.BeansException;

/**
 * TODO 一句话描述
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
