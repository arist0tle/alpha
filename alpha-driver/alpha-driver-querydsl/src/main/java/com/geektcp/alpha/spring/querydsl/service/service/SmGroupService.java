package com.geektcp.alpha.spring.querydsl.service.service;

import com.geektcp.alpha.spring.querydsl.service.model.qo.SmGroupQo;
import com.geektcp.alpha.spring.querydsl.service.model.suo.SmGroupSuo;
import alpha.common.base.model.PageResponse;
import alpha.common.base.model.Response;

/**
 * Created by HaiyangWork on 2018/12/22.
 */
public interface SmGroupService {

    Response saveOrUpdate(SmGroupSuo suo);

    Response delete(SmGroupSuo suo);

    Response find(SmGroupQo qo);

    PageResponse findPage(SmGroupQo qo);

    Response search(SmGroupQo qo);
}
