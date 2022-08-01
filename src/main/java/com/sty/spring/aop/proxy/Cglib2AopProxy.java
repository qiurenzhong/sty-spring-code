package com.sty.spring.aop.proxy;

import com.sty.spring.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib2 代理
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public class Cglib2AopProxy implements AopProxy{
	
	protected final AdvisedSupport advisedSupport;
	
	
	public Cglib2AopProxy(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}
	
	
	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
		enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
		return enhancer.create();
	}
	
	private class DynamicAdvisedInterceptor implements MethodInterceptor {
		protected final AdvisedSupport advised;
		
		public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
			this.advised = advisedSupport;
		}
		
		@Override
		public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
			CglibMethodInvocation invocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
			if (advised.getMethodMatcher().matches(method,advised.getTargetSource().getTarget().getClass())) {
				return advised.getMethodInterceptor().invoke(invocation);
			}
			return invocation.proceed();
		}
	}
	
	private class CglibMethodInvocation extends ReflectiveMethodInvocation{
		private MethodProxy methodProxy;
		
		public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
			super(target,method,arguments);
			this.methodProxy = methodProxy;
		}
		
		@Override
		public Object proceed() throws Throwable {
			return this.methodProxy.invoke(this.target,this.arguments);
		}
	}
}
