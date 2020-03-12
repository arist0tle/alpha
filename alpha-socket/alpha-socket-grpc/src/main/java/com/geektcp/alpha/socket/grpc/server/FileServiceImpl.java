package com.geektcp.alpha.socket.grpc.server;

import com.geektcp.alpha.socket.grpc.annotation.RpcService;
import com.geektcp.alpha.socket.grpc.proto.file.FileData;
import com.geektcp.alpha.socket.grpc.proto.file.FileServiceGrpc;
import com.geektcp.alpha.socket.grpc.proto.file.Response;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * @author tanghaiyang on 2020/1/2 1:18.
 */
@Slf4j
@RpcService
public class FileServiceImpl extends FileServiceGrpc.FileServiceImplBase {

    private static final String dstFilePath = "/share/down/file/jdk-9+181_linux-x64_ri.zip";

    private static FileChannel fileChannel;

    @Override
    public void send(FileData request, StreamObserver<Response> responseObserver) {
        try {
            FileChannel dstFileChannel = getFileChannel();
            if(Objects.isNull(dstFileChannel)){
                return;
            }
            ByteString date = request.getData();
            ByteBuffer buffer = date.asReadOnlyByteBuffer();
            dstFileChannel.write(buffer);
            String message = "response";
            Response response = Response.newBuilder().setMsg(message).build();
            log.info("server responded {}", response);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////
    private static FileChannel getFileChannel() {
        if (Objects.isNull(fileChannel)) {
            try {
                File dstFile = new File(dstFilePath);
                FileOutputStream dstFos = new FileOutputStream(dstFile);
                fileChannel = dstFos.getChannel();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return fileChannel;
    }
}
