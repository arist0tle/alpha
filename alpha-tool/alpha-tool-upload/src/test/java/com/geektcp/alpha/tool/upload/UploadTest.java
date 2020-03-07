package com.geektcp.alpha.tool.upload;

import com.alibaba.fastjson.JSON;
import com.geektcp.alpha.common.spring.rest.RestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author tanghaiyang on 2020/3/7 20:03.
 */
@Slf4j
public class UploadTest {


    @Test
    public void name() {
        Assert.assertTrue(true);
    }

    @Test
    public void upload() throws Exception {
        String url = "http://localhost:9000/upload?name=test";
        HashMap<String,Object> map = new HashMap<>();

//        JSONObject response = restService.doPost(url, map,JSONObject.class);
//        log.info("response: \n{}", JSON.toJSONString(response,true));

        Assert.assertTrue(true);
    }

    @Test
    public void multipartFileTest() throws Exception{
        File file = new File("src.txt");
        String path = file.getAbsolutePath();
        InputStream inputStream = new  FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("test",inputStream);


        File destFile = new File("dest.txt");
        multipartFile.transferTo(destFile);




    }
}
