package com.sty.spring.test.event;

import com.sty.spring.context.event.ApplicationContextEvent;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public class CustomEvent extends ApplicationContextEvent {
	/**
	 * Constructs a prototypical Event.
	 *
	 * @param    source    The object on which the Event initially occurred.
	 * @exception IllegalArgumentException  if source is null.
	 */
	public CustomEvent(Object source) {
		super(source);
	}
	
	private Long id;
	private String message;
	
	public CustomEvent(Object source, Long id, String message) {
		super(source);
		this.id = id;
		this.message = message;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
