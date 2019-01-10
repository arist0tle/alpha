package com.geektcp.alpha.spring.shiro.model.vo;

import com.geektcp.alpha.spring.shiro.model.po.UserPo;
import lombok.Data;

/**
 * Created by tanghaiyang on 2019/1/10.
 */
@Data
public class UserVo {
    private String name;

    public UserVo(UserPo po){
        this.name = po.getName();
    }
}
