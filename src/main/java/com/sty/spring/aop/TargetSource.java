package com.sty.spring.aop;

/**
 * 被代理的目标对象
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public class TargetSource {
	
	private final Object target;
	
	public TargetSource(Object target) {
		this.target = target;
	}
	
	public Object getTarget() {
		return target;
	}
	
	public Class<?>[] getTargetClass() {
		return this.target.getClass().getInterfaces();
	}
 }
