package com.geektcp.alpha.design.pattern.proxy;

import java.net.URL;

/**
 * Created by TangHaiyang on 2019/9/21.
 */
public class ImageProxyTest {

    public static void main(String[] args) throws Exception {
        String image = "http://image.jpg";
        URL url = new URL(image);
        HighResolutionImage highResolutionImage = new HighResolutionImage(url);
        ImageProxy imageProxy = new ImageProxy(highResolutionImage);
        imageProxy.showImage();
    }
}
