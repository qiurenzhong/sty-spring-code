package com.sty.spring.test.dao;

import com.sty.spring.beans.BeansException;
import com.sty.spring.beans.factory.BeanClassLoaderAware;
import com.sty.spring.beans.factory.BeanFactory;
import com.sty.spring.beans.factory.BeanFactoryAware;
import com.sty.spring.beans.factory.BeanNameAware;
import com.sty.spring.beans.factory.DisposableBean;
import com.sty.spring.beans.factory.InitializingBean;
import com.sty.spring.context.ApplicationContext;
import com.sty.spring.context.ApplicationContextAware;

public class UserService implements InitializingBean, DisposableBean , BeanNameAware, BeanClassLoaderAware,BeanFactoryAware, ApplicationContextAware {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("Bean Name is：" + beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        System.out.println("ClassLoader：" + beanClassLoader);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
