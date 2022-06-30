package com.sty.sf.beans;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/6/25
 * @version 1.0.0
 */
public class UserService {

    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.getName(uId));
    }

    public String queryUserInfo2() {
        return "查询用户信息：" + userDao.getName(uId);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
