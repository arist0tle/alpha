package com.geektcp.alpha.sys.auth.dao;


import alpha.common.base.jpa.JpaRepo;
import com.geektcp.alpha.sys.auth.model.po.SysRolePo;
import org.springframework.stereotype.Repository;

/**
 * @author tanghaiyang on 2018/1/4.
 */
@Repository
public interface SysRoleDao extends JpaRepo<SysRolePo> {
}
