import com.geektcp.alpha.db.elasticsearch.Application;
import com.geektcp.alpha.db.elasticsearch.rest.client.EsRestClient;
import org.elasticsearch.client.RestClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tanghaiyang on 2019/5/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "")
public class TestEsClient {

    @Autowired
    EsRestClient esRestClient;

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





}
