package alpha.common.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * Created by chengmo on 2018/12/26.
 */
@Data
public class PageQoBase {

    /**
     * 分页查询信息
     */
    @Valid
    @ApiModelProperty(value = "分页查询参数")
    protected PageQo page = new PageQo();
}
