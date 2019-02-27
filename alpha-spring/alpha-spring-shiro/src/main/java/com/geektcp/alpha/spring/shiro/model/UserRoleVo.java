package com.geektcp.alpha.spring.shiro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengmo on 2018/1/4.
 */
@Data
public class UserRoleVo extends UserVo implements Serializable {
    private static final long serialVersionUID = 2L;
    protected List<RoleVo> roles;

    public UserRoleVo(SysUserPo user) {
        super(user);
    }
}
