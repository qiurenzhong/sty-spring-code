package com.sty.sf.beans;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/13
 * @version 1.0.0
 */
public class UserService {

    private String name;

    public void queryUserInfo(){
        System.out.println("查询用户信息: " + name);
    }


    public UserService(String userName){
        this.name = userName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("").append(name);
        return sb.toString();
    }
}
