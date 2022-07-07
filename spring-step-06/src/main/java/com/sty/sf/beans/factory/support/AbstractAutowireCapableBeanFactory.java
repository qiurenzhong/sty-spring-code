package com.sty.sf.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.sty.sf.beans.BeansException;
import com.sty.sf.beans.PropertyValue;
import com.sty.sf.beans.PropertyValues;
import com.sty.sf.beans.factory.config.AutowireCapableBeanFactory;
import com.sty.sf.beans.factory.config.BeanDefinition;
import com.sty.sf.beans.factory.config.BeanPostProcessor;
import com.sty.sf.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 创建bean
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {

        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);

            // 属性填充
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
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
