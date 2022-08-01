package com.sty.spring.context;

import java.util.EventListener;

/**
 * 应用程序侦听器
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
	
	
	/**
	 *  处理事件
	 *
	 * @param event 事件
	 */
    void onApplicationEvent(E event);
}
