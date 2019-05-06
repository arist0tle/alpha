import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.db.elasticsearch.Application;
import com.geektcp.alpha.db.elasticsearch.rest.client.EsRestClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Assert;
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
public class TestEsClient {

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

    @Test
    public void termQuery() {
        QueryBuilder queryBuilder = QueryBuilders.termQuery(wrapField("account_name"),"AC003");
        SearchRequestBuilder searchBuilder = client
                .prepareSearch("crm_dev2")
                .setTypes("tv_achievement_info")
                .setQuery(queryBuilder)
                .setFetchSource(true)
                .setFrom(0)
                .setSize(5);
        log.info("query: {}", searchBuilder.toString());
        SearchResponse response = searchBuilder.get();
        log.info("response:\n{}", JSON.toJSONString(response,true));
    }

    @Test
    public void example(){
        RestClient restClient = esRestClient.getClient("192.168.1.50,192.168.1.51,192.168.1.52:9200");
        RestClient restClient2 = esRestClient.getClient("192.168.1.50,192.168.1.51,192.168.1.52:9200");
        Assert.assertNotNull(restClient);
        Assert.assertNotNull(restClient2);
        assert restClient == restClient2;

        restClient = esRestClient.getClient("elasticsearch://192.168.1.50,192.168.1.51,192.168.1.52:9300");
        Assert.assertNotNull(restClient);

        restClient = esRestClient.getClient("elasticsearch:http//192.168.1.50,192.168.1.51,192.168.1.52:9200");
        Assert.assertNotNull(restClient);
    }


    @Test
    public void test(){
        RestClient restClient = esRestClient.getClient("192.168.1.50,192.168.1.51,192.168.1.52:9200");
//        restClient.performRequest()


    }



    private String wrapField(String field){
        return field + ".keyword";
    }


}
