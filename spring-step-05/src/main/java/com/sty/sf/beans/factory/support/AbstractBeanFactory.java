package com.sty.sf.beans.factory.support;

import com.sty.sf.beans.BeansException;
import com.sty.sf.beans.factory.BeanFactory;
import com.sty.sf.beans.factory.config.BeanDefinition;

/**
 * 抽象类定义模板方法
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName,Object... args) throws BeansException {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object... args) throws BeansException;

}
