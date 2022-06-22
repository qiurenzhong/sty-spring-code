package com.sty.sf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代表了 Bean 对象的工厂，可以存放 Bean 定义到 Map 中以及获取
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName) {
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void setBean(String beanName,BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
