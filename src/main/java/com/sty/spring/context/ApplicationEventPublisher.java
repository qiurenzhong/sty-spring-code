package com.sty.spring.context;

/**
 * 事件发布者的定义和实现
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public interface ApplicationEventPublisher {

    /**
     *  发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
