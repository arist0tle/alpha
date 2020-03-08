package com.geektcp.alpha.socket.grpc.client;

import javax.annotation.PostConstruct;

import com.geektcp.alpha.socket.grpc.example.Greeting;
import com.geektcp.alpha.socket.grpc.example.HelloWorldServiceGrpc;
import com.geektcp.alpha.socket.grpc.example.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */

@Component
public class GRpcClientServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(GRpcClientServiceImpl.class);

    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565).usePlaintext().build();

        helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        Person person = Person.newBuilder().setFirstName(firstName).setLastName(lastName).build();
        LOGGER.info("client sending {}", person);

        Greeting greeting = helloWorldServiceBlockingStub.sayHello(person);
        LOGGER.info("client received {}", greeting);

        return greeting.getMessage();
    }
}
