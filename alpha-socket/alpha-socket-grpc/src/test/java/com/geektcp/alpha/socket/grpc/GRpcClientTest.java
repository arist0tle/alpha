package com.geektcp.alpha.socket.grpc;

import com.geektcp.alpha.socket.grpc.proto.greet.GreetServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.greet.Greeting;
import com.geektcp.alpha.socket.grpc.proto.greet.Person;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
public class GRpcClientTest {

    @Test
    public void startServer() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(2);
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 10000)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);
        Person person = Person.newBuilder().setFirstName("tang").setLastName("hai").build();
        log.info("client sending {}", person);

        Greeting greeting = stub.sayHello(person);
        log.info("client received {}", greeting);
        latch.await();
        Assert.assertTrue(true);
    }

}
