package com.geektcp.alpha.spring.shiro.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by chengmo on 2018/1/4.
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 2L;
    protected Long id;
    protected String userNo;
    protected String name;
    protected String password;
    protected String updateTime;

    public UserVo(SysUserPo user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userNo = user.getUserNo();
    }
}
