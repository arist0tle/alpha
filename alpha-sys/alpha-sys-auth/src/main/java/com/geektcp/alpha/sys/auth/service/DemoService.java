package com.geektcp.alpha.sys.auth.service;


import alpha.common.base.model.PageResponse;
import alpha.common.base.model.Response;
import com.geektcp.alpha.sys.auth.model.qo.SysUserQo;
import com.geektcp.alpha.sys.auth.model.suo.SysUserSuo;
import com.geektcp.alpha.sys.auth.model.vo.SysUserVo;

import java.util.List;

/**
 * @author tanghaiyang on 2018/1/4.
 */
public interface DemoService {

    List<SysUserVo> find(SysUserQo qo);

    PageResponse<SysUserVo> findPage(SysUserQo qo);

    Response<SysUserVo> findUserRoles(SysUserQo qo);

    PageResponse<SysUserVo> findPageUserRoles(SysUserQo qo);

    Response saveOrUpdate(SysUserSuo suo);

    Response delete(Long userId);
}
