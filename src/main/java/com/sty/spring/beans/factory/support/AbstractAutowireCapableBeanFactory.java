package com.sty.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.PropertyValue;
import com.sty.spring.beans.PropertyValues;
import com.sty.spring.beans.factory.Aware;
import com.sty.spring.beans.factory.BeanClassLoaderAware;
import com.sty.spring.beans.factory.BeanFactoryAware;
import com.sty.spring.beans.factory.BeanNameAware;
import com.sty.spring.beans.factory.DisposableBean;
import com.sty.spring.beans.factory.InitializingBean;
import com.sty.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.sty.spring.beans.factory.config.BeanDefinition;
import com.sty.spring.beans.factory.config.BeanPostProcessor;
import com.sty.spring.beans.factory.config.BeanReference;
import com.sty.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 抽象的自动装配Bean工厂
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
		// 判断是否返回了代理对象
		Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
		if (bean != null) {
			return bean;
		}
		return doCreateBean(beanName, beanDefinition, args);
	}

	/**
	 *  创建bean
	 * @param beanName bean名称
	 * @param beanDefinition bean定义
	 * @param args 形成
	 * @return 返回对象
	 */
	private Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
		Object bean;
		try {
			// Cglib代理实例化bean
			bean = createBeanInstance(beanDefinition, beanName, args);

			// 给bean属性填充
			applyPropertyValues(beanName, bean, beanDefinition);

			// 执行bean的初始化方法
			bean = initializeBean(beanName, bean, beanDefinition);

		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}
		// 注册实现DisposableBean接口的Bean对象
		registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

		addSingleton(beanName, bean);
		return bean;
	}

	private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
		if (bean instanceof DisposableBean || StrUtil.isNotBlank(beanDefinition.getDestroyMethodName())) {
			registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
		}
	}

	private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
		// 调用实现Aware接口的类方法
		if (bean instanceof Aware) {
			if (bean instanceof BeanFactoryAware) {
				((BeanFactoryAware) bean).setBeanFactory(this);
			}
			if (bean instanceof BeanClassLoaderAware) {
				((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
			}
			if (bean instanceof BeanNameAware) {
				((BeanNameAware) bean).setBeanName(beanName);
			}
		}

		// 1. 执行BeanPostProcessor 前置处理
		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

		// 2. 执行Bean对象的初始化方法
		try {
			invokeInitMethods(wrappedBean, beanDefinition);
		} catch (Exception e) {
			throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
		}
		// 3. 执行BeanPostProcessor 后置处理
		wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		return wrappedBean;
	}

	private void invokeInitMethods(Object wrappedBean, BeanDefinition beanDefinition) throws Exception {
		// 1.调用实现InitializingBean接口的方法
		if (wrappedBean instanceof InitializingBean) {
			((InitializingBean) wrappedBean).afterPropertiesSet();
		}
		// 2. 注解配置 init-method {判断是为了避免二次执行初始化}
		String initMethodName = beanDefinition.getInitMethodName();
		if (StrUtil.isNotBlank(initMethodName) && !(wrappedBean instanceof InitializingBean)) {
			Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
			// 调用初始化方法
			method.invoke(wrappedBean);
		}

	}

	protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
		Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
		if (bean != null) {
			bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		}
		return bean;
	}

	private Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
		for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
			if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
				InstantiationAwareBeanPostProcessor abap = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
				Object bean = abap.postProcessBeforeInstantiation(beanClass, beanName);
				if (null != bean) {
					return bean;
				}
			}
		}
		return null;
	}

	protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {

		try {
			PropertyValues propertyValues = beanDefinition.getPropertyValues();
			for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
				String name = propertyValue.getName();
				Object value = propertyValue.getValue();

				if (value instanceof BeanReference) {
					BeanReference beanReference = (BeanReference) value;
					value = getBean(beanReference.getBeanName());
				}
				BeanUtil.setFieldValue(bean, name, value);
			}
		} catch (Exception e) {
			throw new BeansException("error setting property value : " + beanName);
		}


	}

	protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object... args) {
		Constructor constructorBean = null;
		Class<?> beanClass = beanDefinition.getBeanClass();
		Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
		for (Constructor<?> constructor : declaredConstructors) {
			if (args != null && constructor.getParameterTypes().length == args.length) {
				constructorBean = constructor;
				break;
			}
		}
		return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorBean, args);

	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}

	@Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existBean, String beanName) throws BeansException {
		List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			Object obj = beanPostProcessor.postProcessBeforeInitialization(existBean, beanName);
			if (obj != null) {
				return obj;
			}
		}
		return existBean;
	}

	@Override
	public Object applyBeanPostProcessorsAfterInitialization(Object existBean, String beanName) throws BeansException {
		List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			Object obj = beanPostProcessor.postProcessAfterInitialization(existBean, beanName);
			if (obj != null) {
				return obj;
			}
		}
		return existBean;
	}
}
