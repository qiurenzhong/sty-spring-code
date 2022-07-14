package com.sty.sf.beans.factory.support;

import com.sty.sf.beans.factory.BeansException;
import com.sty.sf.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化
 *
 * @author tianma
 * @date 2022/6/22
 * @version 1.0.0
 */
public class JdkInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            if (constructor == null) {
                return beanClass.getDeclaredConstructor().newInstance();
            }else {
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new BeansException("",e);
        }
    }
}
