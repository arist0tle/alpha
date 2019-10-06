package com.geektcp.alpha.dubbo.consumer;

import com.geektcp.alpha.dubbo.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextConsumerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy

        try {
            Thread.sleep(1000);
            String hello = demoService.sayHello("Dubbo"); // call remote method
            System.out.println(hello); // get result
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }

    }
}
