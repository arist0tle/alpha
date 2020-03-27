package com.geektcp.alpha.driver.mybatis.service;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.driver.mybatis.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis.model.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author haiyang on 2020-03-27 16:27
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void method() {
        Assert.assertTrue(true);
    }

    @Test
    public void name() {
        UserQo qo = new UserQo();
        PageResponse<UserVo> response = userService.findPage(qo);
        log.info("response: {}", JSON.toJSONString(response,true));
        Assert.assertTrue(true);
    }
}
