package com.sty.spring.aop;

/**
 * 切点接口
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public interface Pointcut {
	
	/**
	 *  获取匹配过滤器
	 * @return 实体类
	 */
	ClassFilter getClassFilter();
	
	/**
	 * 获取方法匹配器
	 * @return 实体类
	 */
	MethodMatcher getMethodMatcher();
}
