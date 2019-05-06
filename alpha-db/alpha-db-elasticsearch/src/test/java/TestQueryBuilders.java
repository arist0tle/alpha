import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Lists;
import com.geektcp.alpha.db.elasticsearch.Application;
import com.geektcp.alpha.db.elasticsearch.rest.client.EsRestClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;

/**
 * Created by tanghaiyang on 2019/5/5.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "")
public class TestQueryBuilders {

    @Autowired
    EsRestClient esRestClient;

    private TransportClient client;

    @Before
    public void init ()throws Exception{
        Settings settings = Settings.builder()
            .put("client.transport.ignore_cluster_name", true)
            .build();
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.49"), Integer.parseInt("9300")));
    }

    private void excute(String index, String type, QueryBuilder queryBuilder){
        SearchRequestBuilder searchBuilder = client
                .prepareSearch(index)
                .setTypes(type)
                .setQuery(queryBuilder)
                .setFetchSource(true)
                .setFrom(0)
                .setSize(5);
        log.info("query: \n{}", searchBuilder.toString());
        SearchResponse response = searchBuilder.get();
        log.info("response:\n{}", JSON.toJSONString(response.getHits(),true));
    }

    @Test
    public void termQuery() {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("account_name" + ".keyword","AC003");
        excute("crm_dev2", "tv_achievement_info", queryBuilder);
    }

    @Test
    public void termsQuery() {
        QueryBuilder queryBuilder = QueryBuilders.termsQuery("manager_name" + ".keyword",  Lists.newArrayList("刘少蕾","赵研博"));
        excute("crm_dev2", "tv_achievement_sum_loan_bal", queryBuilder);
    }

    @Test
    public void rangeQuery() {
        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("rank_in_branch").from(41).includeLower(true).to(45).includeUpper(false);
        excute("crm_dev2", "tv_achievement_sum_loan_bal", queryBuilder);
    }



    @Test
    public void boolQuery() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        QueryBuilder termQuery = QueryBuilders.termQuery("manager_name" + ".keyword","王旭");
        QueryBuilder rangeQuery = QueryBuilders.rangeQuery("rank_in_bank").from(3).includeLower(true).to(10).includeUpper(false);
        queryBuilder.must(termQuery);
        queryBuilder.must(rangeQuery);
        excute("crm_dev2", "tv_achievement_sum_loan_bal", queryBuilder);
    }

    @Test
    public void matchQuery() {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("manager_name", "刘少蕾 赵研博");
        excute("crm_dev2", "tv_achievement_sum_loan_bal", queryBuilder);
    }

    @Test
    public void multiMatchQuery() {
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("刘少蕾 赵研博", "manager_name");
//        , Lists.newArrayList("刘少蕾","赵研博")
//        queryBuilder.field("manager_name", 1.1f);
        excute("crm_dev2", "tv_achievement_sum_loan_bal", queryBuilder);
    }

    @Test
    public void existsQuery() {
        QueryBuilder queryBuilder = QueryBuilders.existsQuery("manager_name");
        excute("crm_dev2", "tv_achievement_sum_loan_bal", queryBuilder);
    }

}
