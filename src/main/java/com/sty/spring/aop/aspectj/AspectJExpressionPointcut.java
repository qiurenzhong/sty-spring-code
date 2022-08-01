package com.sty.spring.aop.aspectj;

import com.sty.spring.aop.ClassFilter;
import com.sty.spring.aop.MethodMatcher;
import com.sty.spring.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Aspect J表达式切入点
 *
 * @author tianma
 * @date 2022/7/29
 * @version 1.0.0
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
	
	private final PointcutExpression pointcutExpression;
	private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();
	
	static {
		SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
	}
	
	
	public AspectJExpressionPointcut(String expression) {
		PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
		pointcutExpression = pointcutParser.parsePointcutExpression(expression);
	}
	
	@Override
	public boolean matches(Class<?> clazz) {
		return pointcutExpression.couldMatchJoinPointsInType(clazz);
	}
	
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
	}
	
	@Override
	public ClassFilter getClassFilter() {
		return this;
	}
	
	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}
}
