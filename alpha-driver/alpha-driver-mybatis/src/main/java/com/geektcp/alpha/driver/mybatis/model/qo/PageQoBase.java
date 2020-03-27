package com.geektcp.alpha.driver.mybatis.model.qo;

import com.baomidou.mybatisplus.plugins.Page;
import com.geektcp.alpha.driver.mybatis.model.qo.PageQo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * @author haiyang on 3/9/20 6:52 PM.
 */
@Data
public class PageQoBase {

    /**
     * 分页查询信息
     */
    @Valid
    @ApiModelProperty(value = "分页查询参数")
    protected PageQo pageQo = new PageQo();

    @Valid
    @ApiModelProperty(value = "分页查询参数mybatis puls")
    protected Page page = new Page();

}
