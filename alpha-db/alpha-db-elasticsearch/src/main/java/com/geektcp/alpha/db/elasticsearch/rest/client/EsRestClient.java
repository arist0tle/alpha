package com.geektcp.alpha.db.elasticsearch.rest.client;

import com.geektcp.alpha.db.elasticsearch.bean.StoreURL;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tanghaiyang on 2018/11/5.
 */
@Slf4j
@Repository("esRestClient")
public class EsRestClient {
    private static final int DEFAULT_HTTP_PORT = 9200;

    private static final Map<StoreURL, RestClient> POOL = new ConcurrentHashMap<>();

    @PreDestroy
    public void cleanup() {
        POOL.values().forEach(restClient -> {
            try {
                restClient.close();
            } catch (IOException e) {
                log.warn("close com.geektcp.alpha.db.elasticsearch.rest.client got exception ", e);
            }
        });
    }

    /**
     * Get or create a com.geektcp.alpha.db.elasticsearch.rest com.geektcp.alpha.db.elasticsearch.rest.client.
     *
     * @param url 192.168.1.50,192.168.1.51,192.168.1.52:9200
     *            elasticsearch://192.168.1.50,192.168.1.51,192.168.1.52:9300
     *            elasticsearch:http//192.168.1.50,192.168.1.51,192.168.1.52:9200
     * @return
     */
    public RestClient getClient(String url) {
        StoreURL storeURL = new StoreURL();
        storeURL.setUrl(url);
        return getClient(storeURL);
    }

    public RestClient getClient(StoreURL storeURL) {
        try {
            RestClient client = POOL.get(storeURL);
            if (client == null) {
                client = create(storeURL);
                POOL.put(storeURL, client);
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ///////////////////////
    // private functions
    ///////////////////////
    private RestClient create(String url) {
        StoreURL storeURL = new StoreURL();
        storeURL.setUrl(url);
        return create(storeURL);
    }

    private RestClient create(StoreURL storeURL) {
        RestClient restClient = null;
        String url = storeURL.getUrl();
        try {
            if (StringUtils.contains(url, ":http//")) {
                url = url.split(":http//")[1];
            }
            if (StringUtils.contains(url, "://")) {
                url = url.split("://")[1];
                url = getHttpUrlByTcp(url);
            }
            String[] hosts = StringUtils.substringBefore(url, ":").split(",");
            int port = Integer.parseInt(StringUtils.substringAfter(url, ":"));

            // com.geektcp.alpha.db.elasticsearch.rest com.geektcp.alpha.db.elasticsearch.rest.client builder
            List<HttpHost> httpHosts = new ArrayList<>();
            for (String host : hosts) {
                httpHosts.add(new HttpHost(host, DEFAULT_HTTP_PORT));
            }
            restClient = RestClient.builder(httpHosts.toArray(new HttpHost[]{})).build();
            log.info("success to create elasticsearch com.geektcp.alpha.db.elasticsearch.rest com.geektcp.alpha.db.elasticsearch.rest.client url[{0}]", url);
        } catch (Exception e) {
            log.error("failed to create elasticsearch com.geektcp.alpha.db.elasticsearch.rest com.geektcp.alpha.db.elasticsearch.rest.client url[{0}].\n", e, url);
        }
        return restClient;
    }

    private String getHttpUrlByTcp(String tcpUrl) {
        tcpUrl = StringUtils.substringBefore(tcpUrl, ":");
        tcpUrl += ":" + DEFAULT_HTTP_PORT;
        return tcpUrl;
    }

    public void testConnect(String url) {
        try (RestClient client = create(url)) {
            org.elasticsearch.client.Response response = client.performRequest("GET", "_cluster/health", Collections.emptyMap());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode < 300) {
            } else {
                throw new RuntimeException(response.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("test connect to [" + url + "] fail", e);
        }
    }
}