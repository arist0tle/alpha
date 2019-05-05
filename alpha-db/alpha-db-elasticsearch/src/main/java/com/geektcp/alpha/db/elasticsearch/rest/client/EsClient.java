package com.geektcp.alpha.db.elasticsearch.rest.client;

import com.geektcp.alpha.db.elasticsearch.bean.StoreURL;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chengmo on 2017/12/01.
 */
@Slf4j
@Repository("esClient")
public class EsClient {

    private static final Map<StoreURL, TransportClient> POOL = new ConcurrentHashMap<>();

    @PreDestroy
    public void cleanup() {
        POOL.values().forEach(TransportClient::close);
    }

    /**
     * Get or create TCP com.geektcp.alpha.db.elasticsearch.rest.client.
     *
     * @param url 192.168.1.50,192.168.1.51,192.168.1.52:9300
     *            elasticsearch://192.168.1.50,192.168.1.51,192.168.1.52:9300
     * @return
     */
    public TransportClient getClient(String url) {
        return null;
    }

    public TransportClient getClient() {
        return null;
    }

    public TransportClient getClient(StoreURL storeURL) {
        try {
            TransportClient client = POOL.get(storeURL);
            if (client == null){
                client = create(storeURL);
                POOL.put(storeURL, client);
            }
            return client;
        } catch (Exception e) {
            log.error("cannot get com.geektcp.alpha.db.elasticsearch.rest.client [" + storeURL.getUrl() + "]", e);
            throw new RuntimeException(e);
        }
    }

    ///////////////////////
    // private functions
    ///////////////////////
    private TransportClient create(StoreURL storeURL) {
        return create(storeURL.getUrl());
    }

    private TransportClient create(String url) {
        TransportClient client = null;
        try {
            if (StringUtils.contains(url, "://")) {
                url = url.split("://")[1];
            }
            String[] hosts = StringUtils.substringBefore(url, ":").split(",");
            String port = StringUtils.substringAfter(url, ":");
            port = StringUtils.substringBefore(port, "/");
            Settings settings = Settings.builder()
                    .put("com.geektcp.alpha.db.elasticsearch.rest.client.transport.ignore_cluster_name", true)
                    .build();
            client = new PreBuiltTransportClient(settings);
            for (String host : hosts) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), Integer
                        .parseInt(port)));
            }
            log.info("success to create com.geektcp.alpha.db.elasticsearch.rest.client of elasticsearch server url[{0}]", url);
            if(client.connectedNodes().size() == 0){
                throw new RuntimeException("the ES com.geektcp.alpha.db.elasticsearch.rest.client doesn't connect to the ES server url[" + url + "]");
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to create com.geektcp.alpha.db.elasticsearch.rest.client of elasticsearch server url[" + url + "].\n", e);
        }
        return client;
    }

    public void testConnect(String url){
        try (TransportClient client = create(url)){
            ClusterHealthResponse response = client.admin().cluster().health(Requests.clusterHealthRequest()).actionGet(TimeValue.timeValueMinutes(10));
            int numberOfNodes = response.getNumberOfNodes();
            log.info("numberOfNodes: {}", numberOfNodes);
        }
    }
}