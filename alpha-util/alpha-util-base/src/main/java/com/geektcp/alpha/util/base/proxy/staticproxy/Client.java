package com.geektcp.alpha.util.base.proxy.staticproxy;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Client {

    public static void main(String[] args) {
        //UserManagerProxy userManager=new UserManagerImpl();
        UserManagerProxy userManager = new UserManagerImplProxy(new UserManagerImpl());
        userManager.addUser("1111", "张三");
    }
}
