package com.sty.spring.beans.factory.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.FactoryBean;
import com.sty.spring.beans.factory.config.BeanDefinition;
import com.sty.spring.beans.factory.config.BeanPostProcessor;
import com.sty.spring.beans.factory.config.ConfigurableBeanFactory;
import com.sty.spring.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义模板方法
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    private <T> T doGetBean(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            // 若是FactoryBean,则需要调用getObject
            return (T) getObjectForBeanInstance(bean, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition);
    }

    private Object getObjectForBeanInstance(Object bean, String beanName) {
        if (!(bean instanceof FactoryBean)) {
            return bean;
        }
        Object factoryBean = getCachedObjectForFactoryBean(beanName);
        if (factoryBean == null) {
            FactoryBean<?> obj = (FactoryBean<?>) bean;
            factoryBean = getObjectFromFactoryBean(obj, beanName);
        }
        return factoryBean;
    }

    /**
     *  获取BeanDefinition
     * @param beanName bean名称
     * @return BeanDefinition
     * @throws BeansException 异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     *  创建bean
     * @param beanName bean名称
     * @param beanDefinition bean定义
     * @param args  构成函数形参
     * @return 返回bean对象
     * @throws BeansException 异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
