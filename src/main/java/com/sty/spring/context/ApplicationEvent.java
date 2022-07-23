package com.sty.spring.context;

import java.util.EventObject;

/**
 * 定义和实现事件
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param    source    The object on which the Event initially occurred.
     * @exception IllegalArgumentException  if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
