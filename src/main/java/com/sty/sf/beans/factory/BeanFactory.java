package com.sty.sf.beans.factory;

/**
 * Bean工厂
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public interface BeanFactory {

    /**
     *  获取bean,构造函数不带参数
     * @param beanName bean名称
     * @return   返回bean对象
     */
    Object getBean(String beanName) throws BeansException;

    /**
     *  获取bean,构造函数带参数
     * @param beanName bean名称
     * @param args 形参
     * @return 返回bean对象
     * @throws BeansException 异常
     */
    Object getBean(String beanName, Object... args) throws BeansException;

    /**
     *  获取bean
     * @param beanName  bean名称
     * @param requiredType  所属类型
     * @param <T>  泛型
     * @return  返回bean对象
     * @throws BeansException 异常
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;

}
