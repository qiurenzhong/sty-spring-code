package com.sty.spring.context;

import com.sty.spring.beans.BeansException;

/**
 * 可配置的应用程序上下文
 *
 * @author tianma
 * @date 2022/6/30
 * @version 1.0.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     *  刷新容器
     * 加载资源配置文件（XML、properties,Whatever）。
     * 由于此方法是一个初始化方法，因此如果调用此方法失败的情况下，要将其已经创建的Bean销毁。
     * 换句话说，调用此方法以后，要么所有的Bean都实例化好了，要么就一个都没有实例化
     * @throws BeansException 异常
     */
    void refresh() throws BeansException;
}
