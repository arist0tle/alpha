/*
 * Copyright 2019 The nity.io gRPC Spring Boot Project Authors
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

package com.geektcp.alpha.socket.grpc.context;

import io.grpc.Server;
import org.springframework.context.ApplicationEvent;

public class GrpcServerInitializedEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public GrpcServerInitializedEvent(Server source) {
        super(source);
    }

    public Server getServer() {
        return (Server) getSource();
    }
}
