package com.geektcp.alpha.driver.mybatis.service;

import com.geektcp.alpha.driver.mybatis.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis.model.vo.UserVo;

/**
 * @author haiyang on 3/27/20 4:16 PM.
 */
public interface UserService {

    PageResponse<UserVo> findPage(UserQo qo);

}
