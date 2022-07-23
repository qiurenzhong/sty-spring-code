package com.sty.spring.test;

import com.sty.spring.context.support.ClassPathXmlApplicationContext;
import com.sty.spring.test.bean.UserService;
import com.sty.spring.test.event.CustomEvent;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

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

}
