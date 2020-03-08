package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.grpc.protocol.example.Greeting;
import com.geektcp.alpha.grpc.protocol.example.HelloWorldServiceGrpc;
import com.geektcp.alpha.grpc.protocol.example.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.grpc.stub.StreamObserver;
import com.geektcp.alpha.socket.grpc.annotation.GrpcService;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@GrpcService
public class ExampleServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
        LOGGER.info("server received {}", request);

        String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
        Greeting greeting = Greeting.newBuilder().setMessage(message).build();
        LOGGER.info("server responded {}", greeting);

        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }
}
