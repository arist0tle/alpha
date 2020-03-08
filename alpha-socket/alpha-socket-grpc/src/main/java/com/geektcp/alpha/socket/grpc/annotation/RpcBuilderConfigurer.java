
package com.geektcp.alpha.socket.grpc.annotation;

import io.grpc.ServerBuilder;

public interface RpcBuilderConfigurer {
    void configure(ServerBuilder<?> serverBuilder);
}
