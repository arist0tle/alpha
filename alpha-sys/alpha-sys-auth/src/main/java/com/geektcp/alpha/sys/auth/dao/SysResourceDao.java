package com.geektcp.alpha.sys.auth.dao;

import alpha.common.base.jpa.JpaRepo;
import com.geektcp.alpha.sys.auth.model.po.SysResourcePo;
import org.springframework.stereotype.Repository;

/**
 * Created by tanghaiyang on 2018/1/4.
 */
@Repository
public interface SysResourceDao extends JpaRepo<SysResourcePo> {
}
