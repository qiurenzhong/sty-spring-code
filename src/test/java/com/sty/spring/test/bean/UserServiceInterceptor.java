package com.sty.spring.test.bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/8/1
 * @version 1.0.0
 */
public class UserServiceInterceptor implements MethodInterceptor {
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		long start = System.currentTimeMillis();
		try {
			return methodInvocation.proceed();
		} finally {
			System.out.println("监控 - Begin By AOP");
			System.out.println("方法名称：" + methodInvocation.getMethod());
			System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
			System.out.println("监控 - End\r\n");
		}
	}
}
