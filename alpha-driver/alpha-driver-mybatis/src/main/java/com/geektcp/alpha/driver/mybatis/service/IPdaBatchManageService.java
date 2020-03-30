package com.geektcp.alpha.driver.mybatis.service;

import com.geektcp.alpha.driver.mybatis.model.qo.PdaBatchManageQo;
import com.geektcp.alpha.driver.mybatis.model.vo.PageResponseDTO;
import com.geektcp.alpha.driver.mybatis.model.vo.PdaBatchManageVo;

/**
 * @author haiyang on 2020-03-30 13:09
 */
public interface IPdaBatchManageService {

    PageResponseDTO<PdaBatchManageVo> findPage(PdaBatchManageQo pdaBatchManageQo);

}
