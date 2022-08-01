package com.sty.spring.aop.proxy;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 反射方法调用
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
	
	protected final Object target;
	protected final Method method;
	protected final Object[] arguments;
	
	public ReflectiveMethodInvocation(Object target,Method method,Object[] arguments) {
		this.target = target;
		this.method = method;
		this.arguments = arguments;
	}
	
	
	@Override
	public Method getMethod() {
		return method;
	}
	
	@Override
	public Object[] getArguments() {
		return arguments;
	}
	
	@Override
	public Object proceed() throws Throwable {
		return method.invoke(target,arguments);
	}
	
	@Override
	public Object getThis() {
		return target;
	}
	
	@Override
	public AccessibleObject getStaticPart() {
		return method;
	}
}
