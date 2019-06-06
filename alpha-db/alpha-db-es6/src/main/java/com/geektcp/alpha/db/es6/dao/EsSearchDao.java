package com.geektcp.alpha.db.es6.dao;

import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.model.EsQueryResult;

import java.util.List;

/**
 * Created by tanghaiyang on 2019/5/7.
 */
public interface EsSearchDao {

    EsQueryResult search(EsQuery esQuery);

    EsQueryResult searchByIds(EsQuery esQuery);

    EsQueryResult searchByFields(EsQuery esQuery);

    List<EsQueryResult> multiSearch(List<EsQuery> list);

}
