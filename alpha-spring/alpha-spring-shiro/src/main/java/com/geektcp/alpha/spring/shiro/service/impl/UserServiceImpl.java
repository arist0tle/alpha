package com.geektcp.alpha.spring.shiro.service.impl;

import alpha.common.base.jpa.JQL;
import alpha.common.base.jpa.JpaBase;
import alpha.common.base.model.Response;
import com.geektcp.alpha.spring.shiro.dao.UserDao;
import com.geektcp.alpha.spring.shiro.model.po.QUserPo;
import com.geektcp.alpha.spring.shiro.model.po.UserPo;
import com.geektcp.alpha.spring.shiro.model.qo.UserQo;
import com.geektcp.alpha.spring.shiro.model.vo.UserVo;
import com.geektcp.alpha.spring.shiro.service.UserService;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghaiyang on 2019/1/10.
 */
@Service
public class UserServiceImpl  extends JpaBase implements UserService{

    @Autowired
    private UserDao userDao;

    public Response find(UserQo qo){
        QUserPo table = QUserPo.userPo;
        BooleanBuilder builder = new BooleanBuilder();
        if (!StringUtils.isEmpty(qo.getName())) {
            builder = builder.and(table.name.like(JQL.likeWrap(qo.getName())));
        }

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updatedDt"));
        Iterable<UserPo> results = userDao.findAll(builder, sort);
        List<UserVo> rows = new ArrayList<>();
        results.forEach(po -> {
            rows.add(new UserVo(po));
        });
        return Response.success(rows);
    }
}
