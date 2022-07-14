package com.sty.sf.beans.context;

import com.sty.sf.beans.factory.BeansException;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/30
 * @version 1.0.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     *  刷新容器
     * @throws BeansException 异常
     */
    void refresh() throws BeansException;
}
