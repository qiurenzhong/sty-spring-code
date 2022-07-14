package com.sty.spring.beans.factory.config;

/**
 * Bean的引用
 *
 * @author tianma
 * @date 2022/6/25
 * @version 1.0.0
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
