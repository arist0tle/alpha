package com.geektcp.alpha.dubbo.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextApplication {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();

        System.in.read();
    }
}
