package com.geektcp.alpha.socket.grpc;

import com.geektcp.alpha.socket.grpc.proto.file.FileData;
import com.geektcp.alpha.socket.grpc.proto.file.FileServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.file.Response;
import com.geektcp.alpha.socket.grpc.proto.greet.GreetServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.greet.Greeting;
import com.geektcp.alpha.socket.grpc.proto.greet.Person;
import com.google.protobuf.ByteString;
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
    public void startClientForGRpc(){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 10000)
                .usePlaintext()
                .build();
        GreetServiceGrpc.GreetServiceBlockingStub stub = GreetServiceGrpc.newBlockingStub(channel);
        Person person = Person.newBuilder().setFirstName("tang").setLastName("hai").build();
        log.info("client sending {}", person);

        Greeting greeting = stub.sayHello(person);
        log.info("client received {}", greeting);
        Assert.assertTrue(true);
    }

    @Test
    public void startClientForFile(){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 10000)
                .usePlaintext()
                .build();
        ByteString firstName = ByteString.copyFrom("tang".getBytes());
        ByteString lastName = ByteString.copyFrom("hai".getBytes());
        FileServiceGrpc.FileServiceBlockingStub stub = FileServiceGrpc.newBlockingStub(channel);
        FileData fileData = FileData.newBuilder().setFirstName(firstName).setLastName(lastName).build();
        log.info("client sending {}", fileData);
        Response response = stub.send(fileData);
        log.info("client received {}", response);
        channel.shutdown();
        Assert.assertTrue(true);
    }
}
