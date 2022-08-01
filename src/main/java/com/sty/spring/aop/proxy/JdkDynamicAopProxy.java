package com.sty.spring.aop.proxy;

import com.sty.spring.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
	
	private final AdvisedSupport advisedSupport;
	
	public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}
	
	@Override
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTarget().getClass())) {
			MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(proxy,method,args));
		}
		return method.invoke(advisedSupport.getTargetSource().getTarget(),args);
	}
}
