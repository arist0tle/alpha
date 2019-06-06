package com.geektcp.alpha.db.es6.dao.impl;

import com.geektcp.alpha.db.es6.client.EsClient;
import com.geektcp.alpha.db.es6.dao.EsSearchDao;
import com.geektcp.alpha.db.es6.model.EsQuery;
import com.geektcp.alpha.db.es6.model.EsQueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tanghaiyang on 2019/5/7.
 */
public class EsSearchDaoImpl implements EsSearchDao {

    @Autowired
    private EsClient esClient;

    @Override
    public EsQueryResult search(EsQuery esQuery) {
        return null;
    }

    @Override
    public EsQueryResult searchByIds(EsQuery esQuery) {
        return null;
    }

    @Override
    public EsQueryResult searchByFields(EsQuery esQuery) {
        return null;
    }

    @Override
    public List<EsQueryResult> multiSearch(List<EsQuery> list) {
        return null;
    }
}
