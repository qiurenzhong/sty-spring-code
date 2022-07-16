package com.sty.spring.beans.factory;

import com.sty.spring.beans.BeansException;

/**
 *  初始化Bean接口
 *
 * @author tianma
 * @date 2022/7/14
 * @version 1.0.0
 */
public interface InitializingBean {

    /**
     *   bean 属性填充后调用
     * @throws Exception 异常
     */
    void afterPropertiesSet() throws Exception;
}
