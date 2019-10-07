package com.geektcp.alpha.dubbo.consumer;

import com.geektcp.alpha.dubbo.api.ThyService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextConsumerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        ThyService thyService = (ThyService) context.getBean("thyService"); // get remote service proxy

        try {
            Thread.sleep(1000);
            String hello = thyService.sayHello("Dubbo"); // call remote method
            System.out.println(hello); // get result
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }

    }
}
