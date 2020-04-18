package com.geektcp.alpha.spring.security.constant;

import lombok.Data;

/**
 * @author haiyang on 2020-04-18 09:53
 */
public enum LoginStatus implements Status {

    ERROR_LOGIN_FAILED(10,"password parse failed!");

    private int code;
    private String desc;

    private LoginStatus(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
