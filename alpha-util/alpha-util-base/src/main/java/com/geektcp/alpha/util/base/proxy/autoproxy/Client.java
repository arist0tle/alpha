package com.geektcp.alpha.util.base.proxy.autoproxy;

import com.geektcp.alpha.util.base.proxy.staticproxy.UserManagerImpl;
import com.geektcp.alpha.util.base.proxy.staticproxy.UserManagerProxy;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Client {

    public static void main(String[] args) {
        LogHandler logHandler = new LogHandler();
        UserManagerProxy userManager = (UserManagerProxy) logHandler.newProxyInstance(new UserManagerImpl());
        //UserManager userManager=new UserManagerImpl();
        userManager.addUser("1111", "张三");
    }

}
