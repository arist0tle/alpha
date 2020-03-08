package com.geektcp.alpha.socket.grpc.client;

import com.geektcp.alpha.socket.grpc.demo.GreetingServiceGrpc;
import com.geektcp.alpha.socket.grpc.demo.HelloRequest;
import com.geektcp.alpha.socket.grpc.demo.HelloResponse;
import com.geektcp.alpha.socket.grpc.demo.Sentiment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
public class ThyGRpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.greeting(
                HelloRequest.newBuilder()
                        .setName("Ray")
                        .setAge(18)
                        .setSentiment(Sentiment.HAPPY)
                        .build());
        log.info("helloResponse:{}",helloResponse);
        channel.shutdown();
    }
}
