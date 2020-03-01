package com.geektcp.alpha.spring.shiro.monitor.service;

import com.geektcp.alpha.spring.shiro.entity.monitor.ActiveUser;

import java.util.List;

/**
 * @author MrBird
 */
public interface ISessionService {

    /**
     * 获取在线用户列表
     *
     * @param username 用户名
     * @return List<ActiveUser>
     */
    List<ActiveUser> list(String username);

    /**
     * 踢出用户
     *
     * @param sessionId sessionId
     */
    void forceLogout(String sessionId);
}
