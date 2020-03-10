package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.socket.grpc.annotation.RpcService;
import com.geektcp.alpha.socket.grpc.proto.file.FileData;
import com.geektcp.alpha.socket.grpc.proto.file.FileServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.file.Response;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
@RpcService
public class FileServiceImpl extends FileServiceGrpc.FileServiceImplBase {

    @Override
    public void send(FileData request, StreamObserver<Response> responseObserver) {
        log.info("server received {}", request);
        String firstName = new String(request.getFirstName().toByteArray());
        String lastName = new String(request.getLastName().toByteArray());
        String message = "Hello " + firstName + " | " + lastName + "!";
        ByteString bs = ByteString.copyFrom(message.getBytes());
        Response response = Response.newBuilder().setMessage(bs).build();
        log.info("server responded {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
