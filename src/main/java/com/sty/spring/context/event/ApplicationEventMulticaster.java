package com.sty.spring.context.event;

import com.sty.spring.context.ApplicationEvent;
import com.sty.spring.context.ApplicationListener;

/**
 * 事件广播器
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
