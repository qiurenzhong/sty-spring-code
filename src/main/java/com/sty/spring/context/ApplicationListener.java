package com.sty.spring.context;

import java.util.EventListener;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
