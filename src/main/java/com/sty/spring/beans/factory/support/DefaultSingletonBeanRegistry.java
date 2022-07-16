package com.sty.spring.beans.factory.support;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.DisposableBean;
import com.sty.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 注册单例bean
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String,Object> singletonObjects = new HashMap<>();
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    /**空单例对象的内部标记:用作并发映射(不支持空值)的标记值 */
    protected final static Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     *  增加单列池
     * @param beanName  bean名称
     * @param singletonBean  单例bean
     */
    protected void addSingleton(String beanName,Object singletonBean) {
        singletonObjects.put(beanName,singletonBean);
    }

    /**
     *  注册销毁bean池
     * @param beanName  bean名称
     * @param bean 销毁bean
     */
    public void registerDisposableBean(String beanName,DisposableBean bean) {
        disposableBeans.put(beanName,bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableObj = keySet.toArray();
        for (int i =  disposableObj.length-1;i > 0; i-- ) {
            Object beanName = disposableObj[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);

            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
