package com.geektcp.alpha.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by TangHaiyang on 2019/9/18.
 */
@Slf4j
public class SystemUtils {


    @Test
    public void InetAddress() throws Exception{
        InetAddress inetAddress = InetAddress.getLocalHost();
        log.info(inetAddress.toString());
    }

    @Test
    public void getCores(){
        int cores = Runtime.getRuntime().availableProcessors();
        log.info("cores: " + cores);
    }
}
