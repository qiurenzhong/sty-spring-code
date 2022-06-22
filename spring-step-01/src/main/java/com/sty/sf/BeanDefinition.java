package com.sty.sf;

/**
 * 用于定义 Bean 实例化信息，现在的实现是以一个 Object 存放对象
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean){
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
