package com.haizhi.utitl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
public class HttpUtil {
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
    private static final int CONNECT_TIMEOUT = 120*1000;
    private static final int REQUEST_TIMEOUT = 60*1000;
    private static final int SOCKET_TIMEOUT = 60*1000;

    public static void main(String[] args) {
//        System.out.println( wexinRESTfulPost( "test code java2222" )  );
        try{
            throw new SQLException();
        } catch (Exception e) {
//        e.printStackTrace();
            System.out.println("e.getMessage: " + e.getMessage() );
            HttpUtil.wexinRESTfulPost(e.getMessage());
        }

    }

    public static String restfulDelete(String url) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpDelete datadump = new HttpDelete(url);

        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);
        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);
        CloseableHttpResponse response = null;
        String result = "";

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();
        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

        return result;
    }

    public static String restfulGet(String url) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpGet datadump = new HttpGet(url);

        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);
        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);
        CloseableHttpResponse response = null;
        String result = "";

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();
        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

        return result;
    }


    public static String restfulGet(String url, String username, String password)throws Exception {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpGet datadump = new HttpGet(url);

        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);

        // 添加basic auth认证
        String token = Utitl.getBase64(username + ":" + password);
        datadump.addHeader("Authorization", "Basic "+ token);

        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        confBuilder.setAuthenticationEnabled(true);

        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);
        CloseableHttpResponse response = null;
        String result = "";

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();
        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

        return result;
    }


    public static String restfulPost(String url, String json_post) throws Exception {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpPost datadump = new HttpPost(url);
        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);

        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);

        CloseableHttpResponse response = null;
        String result = "";

        StringEntity entity = new StringEntity(json_post, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        // 将post内容装载到post中
        datadump.setEntity(entity);

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();
//        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
//        }

        response.close();
        client.close();

        return result;
    }


    public static String restfulPost(String url, String json_post,
                                     String username, String password) {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpPost datadump = new HttpPost(url);

        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);

        // 添加basic auth认证
        String token = Utitl.getBase64(username + ":" + password);
        datadump.addHeader("Authorization", "Basic "+ token);

        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);

        CloseableHttpResponse response = null;
        String result = "";

        StringEntity entity = new StringEntity(json_post, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        // 将post内容装载到post中
        datadump.setEntity(entity);

        try {
            response = client.execute(datadump);
            int code = response.getStatusLine().getStatusCode();
            if(code == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null) {
                    response.close();
                } if(client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static String restfulPut(String url) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpPut datadump = new HttpPut(url);

        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);
        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);
        CloseableHttpResponse response = null;
        String result = "";

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();
        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

        return result;
    }

    public static String restfulPut(String url, String json_post) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpPut datadump = new HttpPut(url);
        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);

        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);

        CloseableHttpResponse response = null;
        String result = "";

        StringEntity entity = new StringEntity(json_post, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        // 将post内容装载到post中
        datadump.setEntity(entity);

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();

        log.info("=====" + EntityUtils.toString( response.getEntity() ));

        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

        return result;
    }


    public static String restfulPatch(String url, String json_post) throws Exception{
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);

        CloseableHttpClient client = httpClientBuilder.build();

        HttpPatch datadump = new HttpPatch(url);
        datadump.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        datadump.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);

        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        datadump.setConfig(config);

        CloseableHttpResponse response = null;
        String result = "";

        StringEntity entity = new StringEntity(json_post, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        // 将post内容装载到post中
        datadump.setEntity(entity);

        response = client.execute(datadump);
        int code = response.getStatusLine().getStatusCode();

        log.info(("=====" + EntityUtils.toString( response.getEntity() )));

        if(code == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }

        response.close();
        client.close();

        return result;
    }


    private static String wexinRESTfulPost(String message, int ...args) {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        HttpRequestRetryHandler hrrh = new DefaultHttpRequestRetryHandler();
        HttpClientBuilder httpClientBuilder = hcb.setRetryHandler(hrrh);
        CloseableHttpClient client = httpClientBuilder.build();

        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=xeGIn-4Ze53hfa6RmqJ5hDe8-e8CmI8bJZ5n8L5Ps2no2uLNy9lewQAvwNj7SajE";
        HttpPost post = new HttpPost(url);

        post.addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        post.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);

        RequestConfig.Builder confBuilder = RequestConfig.custom();
        confBuilder.setConnectTimeout(CONNECT_TIMEOUT);
        confBuilder.setConnectionRequestTimeout(REQUEST_TIMEOUT);
        confBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = confBuilder.build();
        post.setConfig(config);

        CloseableHttpResponse response = null;
        String result = "";

        String agentid;
        if(args.length>0) {  agentid = Integer.toString( args[0]); }
        else { agentid = "2"; }

//      json方式
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("touser", "@all");
        jsonParam.put("msgtype", "text");
        jsonParam.put("agentid", agentid);
            JSONObject test = new JSONObject();
            test.put("content", message);
        jsonParam.put("text", test);

        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");

        // 将post内容装载到post中
        post.setEntity(entity);

        try {
            response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if(code == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null) {
                    response.close();
                } if(client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}