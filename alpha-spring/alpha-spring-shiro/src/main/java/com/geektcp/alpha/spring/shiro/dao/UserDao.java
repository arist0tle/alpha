package com.geektcp.alpha.spring.shiro.dao;

import alpha.common.base.jpa.JpaRepo;
import com.geektcp.alpha.spring.shiro.model.po.UserPo;
import org.springframework.stereotype.Repository;

/**
 * Created by tanghaiyang on 2019/1/10.
 */
@Repository
public interface UserDao extends JpaRepo<UserPo> {
}
