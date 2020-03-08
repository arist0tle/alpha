
package com.geektcp.alpha.socket.grpc.annotation;

import io.grpc.ServerBuilder;

public interface GrpcServerBuilderConfigurer {
    void configure(ServerBuilder<?> serverBuilder);
}
