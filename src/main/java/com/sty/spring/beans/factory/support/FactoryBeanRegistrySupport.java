package com.sty.spring.beans.factory.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  FactoryBean 注册服务
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     *  FactoryBean创建的单例对象的缓存:FactoryBean名称——>对象
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object bean = this.factoryBeanObjectCache.get(beanName);
        return (bean != NULL_OBJECT ? bean : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        if (factoryBean.isSingleton()) {
            Object bean = this.factoryBeanObjectCache.get(beanName);
            if (bean == null) {
                bean = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, (bean != null ? bean : NULL_OBJECT));
            }
            return (bean != NULL_OBJECT ? bean : null);
        } else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }

}
