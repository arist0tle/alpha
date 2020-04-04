package com.geektcp.alpha.sso.server.vo;

import com.geektcp.alpha.sso.server.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author tanghaiyang

 */
@Data
public class SysUserVO extends SysUser {

    /**
     * 权限列表
     */
    private List<String> authorityList;

}
