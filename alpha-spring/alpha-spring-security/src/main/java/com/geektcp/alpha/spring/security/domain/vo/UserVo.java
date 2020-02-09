package com.geektcp.alpha.spring.security.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by Shuangyao
 * 23:10 2018/9/6
 */
@Data
public class UserVo {

    private String userName;
    private String userDesc;
    private List<String> roleCodes;

}
