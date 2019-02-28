package com.geektcp.alpha.sys.auth.service;


import alpha.common.base.model.Response;
import com.geektcp.alpha.sys.auth.model.uo.SysRoleResourceUo;

/**
 * Created by tanghaiyang on 2018/1/4.
 */
public interface SysRoleService {

    Response grantResources(SysRoleResourceUo uo);

    Response findAllResources(Long roleId);
}
