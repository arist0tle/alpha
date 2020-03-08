package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.socket.grpc.demo.GreetingServiceGrpc;
import com.geektcp.alpha.socket.grpc.demo.HelloRequest;
import com.geektcp.alpha.socket.grpc.demo.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by rayt on 5/16/16.
 */
@Slf4j
public class ThyGRpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl()).build();

        log.info("Starting server...");
        server.start();
        log.info("Server started!");
        server.awaitTermination();
    }

    public static class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
        @Override
        public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            log.info("request: {}", request);

            String greeting = "Hello there, " + request.getName();

            HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
