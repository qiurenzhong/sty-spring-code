package com.sty.spring.beans.factory.config;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.PropertyValues;

/**
 *  实例化感知Bean后处理器
 *
 * @author tianma
 * @date 2022/7/14
 * @version 1.0.0
 */
public interface InstantiationAwareBeanPostProcessor extends  BeanPostProcessor{

    /**
     * 在Bean 对象执行初始化方法之前，执行此方法
     * @param beanClass  bean类
     * @param beanName bean名称
     * @return 返回对象
     * @throws BeansException 异常
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     *  在Bean 对象执行初始化方法之后，执行此方法
     * @param bean bean对象
     * @param beanName bean名称
     * @return 返回对象
     * @throws BeansException 异常
     */
    Object postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     *  在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     * @param pvs 用于传递类中的属性信息
     * @param bean  bean对象
     * @param beanName bean名称
     * @return 返回对象
     * @throws BeansException 异常
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws  BeansException;

    /**
     * 在 Spring 中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     * @param bean bean对象
     * @param beanName bean名称
     * @return 返回对象
     */
    default Object getEarlyBeanReference(Object bean,String beanName) {
        return  bean;
    }
}
