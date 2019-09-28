package com.geektcp.alpha.sys.auth.service;


import alpha.common.base.model.PageResponse;
import alpha.common.base.model.Response;
import com.geektcp.alpha.sys.auth.model.qo.SysUserQo;
import com.geektcp.alpha.sys.auth.model.suo.SysUserSuo;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public interface SysUserService {

    PageResponse findPage(SysUserQo qo);

    boolean existsUser(String userNo);

    Response confirmPassword(String password);

    Response detail();

    Response save(SysUserSuo suo);

    Response update(SysUserSuo suo);

    Response delete(SysUserSuo suo);

    Response updatePassword(SysUserSuo suo);

}
