/*
 * Copyright 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.grpc.protocol.demo.GreetingServiceGrpc;
import com.geektcp.alpha.grpc.protocol.demo.HelloRequest;
import com.geektcp.alpha.grpc.protocol.demo.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by rayt on 5/16/16.
 */
@Slf4j
public class DemoGrpcServer {
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
