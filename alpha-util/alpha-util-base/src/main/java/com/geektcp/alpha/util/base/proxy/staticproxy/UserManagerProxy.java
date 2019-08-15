package com.geektcp.alpha.util.base.proxy.staticproxy;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public interface UserManagerProxy {

    void addUser(String userId, String userName);

    void delUser(String userId);

    String findUser(String userId);

    void modifyUser(String userId, String userName);

}
