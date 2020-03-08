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

package com.geektcp.alpha.socket.grpc.client;

import com.geektcp.alpha.grpc.protocol.demo.GreetingServiceGrpc;
import com.geektcp.alpha.grpc.protocol.demo.HelloRequest;
import com.geektcp.alpha.grpc.protocol.demo.HelloResponse;
import com.geektcp.alpha.grpc.protocol.demo.Sentiment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by rayt on 5/16/16.
 */
@Slf4j
public class DemoClient {
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
