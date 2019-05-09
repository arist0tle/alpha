package com.geektcp.alpha.db.es6.search;

import com.geektcp.alpha.db.es6.client.EsClient;
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
