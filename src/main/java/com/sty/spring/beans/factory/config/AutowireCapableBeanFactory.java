package com.sty.spring.beans.factory.config;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.BeanFactory;

/**
 *  自动装配工厂bean
 *
 * @author tianma
 * @date 2022/7/3
 * @version 1.0.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     *  执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existBean, String beanName) throws BeansException;


    /**
     *  执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization  方法
     * @param existBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existBean, String beanName) throws BeansException;

}
