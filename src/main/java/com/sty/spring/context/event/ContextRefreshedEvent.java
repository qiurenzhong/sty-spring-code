package com.sty.spring.context.event;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param    source    The object on which the Event initially occurred.
     * @exception IllegalArgumentException  if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
