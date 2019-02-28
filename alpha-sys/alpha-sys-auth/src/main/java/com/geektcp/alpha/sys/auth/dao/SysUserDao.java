package com.geektcp.alpha.sys.auth.dao;

import alpha.common.base.jpa.JpaRepo;
import com.geektcp.alpha.sys.auth.model.po.SysUserPo;
import org.springframework.stereotype.Repository;

/**
 * Created by tanghaiyang on 2018/1/4.
 */
@Repository
public interface SysUserDao extends JpaRepo<SysUserPo> {

    SysUserPo findById(Long id);

    SysUserPo findByUserNo(String userNo);
}
