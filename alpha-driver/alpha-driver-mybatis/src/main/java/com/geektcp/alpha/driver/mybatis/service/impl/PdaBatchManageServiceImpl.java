package com.geektcp.alpha.driver.mybatis.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.geektcp.alpha.driver.mybatis.dao.PdaBatchManageMapper;
import com.geektcp.alpha.driver.mybatis.model.po.PdaBatchManageEntity;
import com.geektcp.alpha.driver.mybatis.model.qo.PageQo;
import com.geektcp.alpha.driver.mybatis.model.qo.PdaBatchManageQo;
import com.geektcp.alpha.driver.mybatis.model.vo.PageResponseDTO;
import com.geektcp.alpha.driver.mybatis.model.vo.PdaBatchManageVo;
import com.geektcp.alpha.driver.mybatis.service.IPdaBatchManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haiyang on 2020-03-30 13:10
 */

@Slf4j
@Service
public class PdaBatchManageServiceImpl extends ServiceImpl<PdaBatchManageMapper, PdaBatchManageEntity> implements IPdaBatchManageService {

    @Override
    public PageResponseDTO<PdaBatchManageVo> findPage(PdaBatchManageQo qo) {
        PageQo pageQo = qo.getPageQo();
        Wrapper<PdaBatchManageEntity> wrapper = new EntityWrapper<>();
        Page<PdaBatchManageEntity> page = new Page<>();
        page.setCurrent(pageQo.getPageNo());
        page.setSize(pageQo.getPageSize());
        PageHelper.startPage(pageQo.getPageNo(), pageQo.getPageSize());
        List<PdaBatchManageEntity> poList = this.selectList(wrapper);
        PageInfo<PdaBatchManageEntity> pageInfo = new PageInfo<>(poList);
        log.info("poList: {}", JSON.toJSONString(poList,true));
        log.info("pageInfo: {}", JSON.toJSONString(pageInfo, true));
        return null;
    }
}

