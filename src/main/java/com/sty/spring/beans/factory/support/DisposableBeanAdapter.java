package com.sty.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.DisposableBean;
import com.sty.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 销毁bean适配器
 *
 * @author tianma
 * @date 2022/7/16
 * @version 1.0.0
 */
public class DisposableBeanAdapter implements DisposableBean {

	private final Object bean;
	private final String beanName;
	private String destroyMethodName;

	public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
		this.bean = bean;
		this.beanName = beanName;
		this.destroyMethodName = beanDefinition.getDestroyMethodName();
	}

	@Override
	public void destroy() throws Exception {

		// 1. 实现接口DisposableBean
		if (bean instanceof DisposableBean) {
			((DisposableBean) bean).destroy();
		}

		// 2. 注解配置 destroy-method {判断是为了避免二次执行初始化}
		if (StrUtil.isNotBlank(destroyMethodName) && !(bean instanceof DisposableBean)) {
			Method method = this.bean.getClass().getMethod(destroyMethodName);
			if (method == null) {
				throw new BeansException("Could not find an destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
			}
			// 调用销毁方法
			method.invoke(destroyMethodName);
		}
	}
}
