package com.sty.spring.beans.factory.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.core.io.Resource;
import com.sty.spring.core.io.ResourceLoader;

/**
 *  Bean的定义读取接口
 *
 * @author tianma
 * @date 2022/6/29
 * @version 1.0.0
 */
public interface BeanDefinitionReader {

    /**
     *  注册
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     *  加载
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     *  加载资源
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     *  加载资源
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resource) throws BeansException;

    /**
     *  加载资源
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     *  加载资源
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String... location) throws BeansException;
}
