package com.sty.spring.test;

import com.sty.spring.aop.AdvisedSupport;
import com.sty.spring.aop.TargetSource;
import com.sty.spring.aop.aspectj.AspectJExpressionPointcut;
import com.sty.spring.aop.proxy.Cglib2AopProxy;
import com.sty.spring.aop.proxy.JdkDynamicAopProxy;
import com.sty.spring.context.support.ClassPathXmlApplicationContext;
import com.sty.spring.test.bean.UserService;
import com.sty.spring.test.bean.UserServiceInterceptor;
import com.sty.spring.test.bean.api.IUserService;
import com.sty.spring.test.event.CustomEvent;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Method;

/**
 *  单元测试类
 *
 * @author tianma
 * @date 2022/7/7
 * @version 1.0.0
 */
public class ApiTest {

    @Test
    public void test_createContext(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");

        // 2.获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.queryUserInfo();
        System.out.println(userInfo);

    }

    @Test
    public void test_initOrDestroy(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");

        // 注册钩子
        applicationContext.registerShutdownHook();

        // 2.获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.queryUserInfo();
        System.out.println(userInfo);

    }

    @Test
    public void test_aware(){
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");

        // 注册钩子
        applicationContext.registerShutdownHook();

        // 2.获取bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.queryUserInfo();
        System.out.println(userInfo);
        System.out.println("ApplicationContextAware："+ userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());

    }

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
	
	@Test
	public void test_event() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
		applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
		
		applicationContext.registerShutdownHook();
	}
	
	@Test
	public void test_aop() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.sty.spring.test.bean.UserService.*(..))");
		Class<UserService> serviceClass = UserService.class;
		try {
			Method queryUserInfo = serviceClass.getDeclaredMethod("queryUserInfo");
			System.out.println(pointcut.matches(serviceClass));
			System.out.println(pointcut.matches(queryUserInfo,serviceClass));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_dynamic() {
		// 目标对象
		IUserService userService = new UserService();
		
		// 组装代理信息
		AdvisedSupport advisedSupport = new AdvisedSupport();
		advisedSupport.setTargetSource(new TargetSource(userService));
		advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
		advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.sty.spring.test.bean.api.IUserService.*(..))"));
		
		// 代理对象(JdkDynamicAopProxy)
		IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
		// 测试调用
		System.out.println("测试结果：" + proxy_jdk.queryUserInfo());
		
		// 代理对象(Cglib2AopProxy)
		IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
		// 测试调用
		System.out.println("测试结果：" + proxy_cglib.register("花花"));
	}
}
