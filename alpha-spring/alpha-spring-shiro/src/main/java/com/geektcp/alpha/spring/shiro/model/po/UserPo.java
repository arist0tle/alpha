package com.geektcp.alpha.spring.shiro.model.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tanghaiyang on 2019/1/10.
 */
@Data
@Entity
@Table(name = "sys_resource")
public class UserPo {
    private String name;
}
