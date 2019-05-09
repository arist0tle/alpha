package com.geektcp.alpha.db.es6.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tanghaiyang on 2019/5/7.
 */
@Slf4j
@Repository
public class EsClient {

    private static final Map<String, RestHighLevelClient> POOL = new ConcurrentHashMap<>();

    private static RestHighLevelClient restHighLevelClient;
    private static RestClient restClient;

    public RestHighLevelClient getRestHighLevelClient() {
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.1.101", 9200, "http")));
        return restHighLevelClient;
    }

   public void close()throws Exception{
       restHighLevelClient.close();
   }

    public RestClient getRestClient(){
        HttpHost httpHost = new HttpHost("192.168.1.101", 9200, "http");
        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password"));

        // set timeout
        restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder.setConnectTimeout(5000)
                        .setSocketTimeout(60000);
            }
        }).setMaxRetryTimeoutMillis(60000);

        // set thread count and basic auth
        restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                return httpClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(3).build())
                        .setDefaultCredentialsProvider(credentialsProvider);
            }
        });

        restClient = restClientBuilder.build();
        return restClient;
    }

    //////////////////////////
    /////// private function
    /////////////////////////
//    private RestHighLevelClient create(String url ,String clusterName) {
//        RestClientBuilder clientBuilder = null;
//        RestHighLevelClient restClient = null;
//        try {
//            HttpHost[] hostArray = this.getHostArray(url);
//            if ("true".equals(esSecureMode)) {
//                this.setSecConfig();
//                clientBuilder = RestClient.builder(hostArray);
//            } else {
//                System.setProperty("es.security.indication", "false");
//                clientBuilder = RestClient.builder(hostArray);
//            }
//
//            Header[] defaultHeaders = new Header[] { new BasicHeader("Accept", "application/json"),
//                    new BasicHeader("Content-type", "application/json") };
//
//            if ("true".equals(uniqueConnectTimeConfig)) {
//                setConnectTimeOutConfig(clientBuilder);
//            }
//
//            if ("true".equals(uniqueConnectNumConfig)) {
//                setMutiConnectConfig(clientBuilder);
//            }
//
//            clientBuilder = clientBuilder.setMaxRetryTimeoutMillis(maxRetryTimeoutMillis);
//            clientBuilder.setDefaultHeaders(defaultHeaders);
//            restClient = new RestHighLevelClient(clientBuilder);
//            LOG.info("The RestClient has been created !");
//            //restClient.setHosts(hostArray);
//        } catch (Exception e) {
//            LOG.error("failed to create client of elasticsearch server cluster[{0}] url[{1}].\n", e, clusterName, url);
//        }
//        return restClient;
//    }
//
//    private void setConnectTimeOutConfig(RestClientBuilder clientBuilder) {
//        clientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//            @Override
//            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                requestConfigBuilder.setConnectTimeout(connectTimeout);
//                requestConfigBuilder.setSocketTimeout(SocketTimeout);
//                requestConfigBuilder.setConnectionRequestTimeout(maxRetryTimeoutMillis);
//                return requestConfigBuilder;
//            }
//        });
//    }
//
//
//    private void setMutiConnectConfig(RestClientBuilder clientBuilder) {
//        clientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                httpClientBuilder.setMaxConnTotal(maxConnectNum);
//                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
//                return httpClientBuilder;
//            }
//        });
//    }

    private HttpHost[] getHostArray(String url) throws Exception {
        return null;
    }
}
