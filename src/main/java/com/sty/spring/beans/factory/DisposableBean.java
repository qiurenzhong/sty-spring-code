package com.sty.spring.beans.factory;

import com.sty.spring.beans.BeansException;

/**
 *  销毁bean接口
 *
 * @author tianma
 * @date 2022/7/14
 * @version 1.0.0
 */
public interface DisposableBean {

    /**
     *  销毁
     * @throws Exception 异常
     */
    void destroy() throws Exception;
}
