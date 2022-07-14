package com.sty.sf.beans.factory.config;

/**
 * 单例注册接口定义和实现
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public interface SingletonBeanRegistry {

    /**
     *  获取单例
     * @param beanName bean名称
     * @return 返回对象
     */
    Object getSingleton(String beanName);
    
}
