package com.geektcp.alpha.spring.shiro.constant;

/**
 * Created by tanghaiyang on 2019/1/10.
 */
public enum AuthStatus {

    RESOURCE_FIND_ERROR(10502, "分组查询失败"),
    RESOURCE_SAVE_ERROR(10503, "版本保存失败"),
    RESOURCE_DELETE_ERROR(10504, "版本删除失败"),
            ;

    private int code;
    private String desc;

    AuthStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
