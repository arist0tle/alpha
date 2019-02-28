package com.geektcp.alpha.sys.auth.dao;

import alpha.common.base.jpa.JpaRepo;
import com.geektcp.alpha.sys.auth.model.po.SysRoleResourcePo;
import org.springframework.stereotype.Repository;

/**
 * Created by tanghaiyang on 2018/1/4.
 */
@Repository
public interface SysRoleResourceDao extends JpaRepo<SysRoleResourcePo> {
}
