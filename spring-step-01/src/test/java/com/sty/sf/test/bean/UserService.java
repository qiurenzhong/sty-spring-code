package com.sty.sf.test.bean;

/**
 * 定义了一个 UserService 对象，方便我们后续对 Spring 容器测试
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class UserService {

    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }
}
