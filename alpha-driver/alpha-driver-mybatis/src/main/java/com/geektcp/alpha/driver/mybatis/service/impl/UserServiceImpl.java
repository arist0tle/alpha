package com.geektcp.alpha.driver.mybatis.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis.dao.UserDao;
import com.geektcp.alpha.driver.mybatis.model.qo.PageQo;
import com.geektcp.alpha.driver.mybatis.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis.model.po.UserPo;
import com.geektcp.alpha.driver.mybatis.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis.model.vo.UserVo;
import com.geektcp.alpha.driver.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haiyang on 3/27/20 4:17 PM.
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, UserPo> implements UserService {

    @Override
    public PageResponse<UserVo> findPage(UserQo qo) {
        Wrapper<UserPo> wrapper = new EntityWrapper<>();
        PageQo pageQo = qo.getPageQo();
        PageHelper.startPage(pageQo.getPageNo(), pageQo.getPageSize());
        List<UserPo> result = this.selectList(wrapper);
        log.info("result: {}", JSON.toJSONString(result, true));
        PageInfo<UserPo> pageInfo = new PageInfo<>(result);
        log.info("pageInfo: {}", JSON.toJSONString(pageInfo, true));
        return new PageResponse();
    }
}
