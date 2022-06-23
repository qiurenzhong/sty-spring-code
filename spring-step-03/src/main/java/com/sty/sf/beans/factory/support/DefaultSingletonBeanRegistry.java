package com.sty.sf.beans.factory.support;

import com.sty.sf.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册单例bean
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object obj) {
        singletonObjects.put(beanName,obj);
    }
}
