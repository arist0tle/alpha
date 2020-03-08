package com.geektcp.alpha.socket.grpc.annotation;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Value("${" + RpcPort.propertyName + "}")
public @interface RpcPort {
    String propertyName = "grpc.server.port";
}
