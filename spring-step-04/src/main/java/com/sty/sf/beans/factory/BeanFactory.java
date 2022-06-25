package com.sty.sf.beans.factory;

import com.sty.sf.beans.BeansException;

/**
 * Bean工厂
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public interface BeanFactory {

    /**
     *  获取bean
     * @param beanName bean名称
     * @param args 形参
     * @return 返回bean对象
     * @throws BeansException 异常
     */
    Object getBean(String beanName,Object... args) throws BeansException;

}
