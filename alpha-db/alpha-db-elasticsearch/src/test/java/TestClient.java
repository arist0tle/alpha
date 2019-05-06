import com.geektcp.alpha.db.elasticsearch.rest.client.EsRestClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

/**
 * Created by tanghaiyang on 2019/5/6.
 */
public class TestClient {

    @Autowired
    EsRestClient esRestClient;

    private TransportClient client;

    @Test
    public void test(){
        RestClient restClient = esRestClient.getClient("192.168.1.50,192.168.1.51,192.168.1.52:9200");
//        restClient.performRequest()
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
    public void buildClient()throws Exception{
        Settings settings = Settings.builder()
                .put("client.transport.ignore_cluster_name", true)
                .build();
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.1.49"), Integer.parseInt("9300")));
    }
}
