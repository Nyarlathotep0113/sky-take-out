package com.sky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

@Configuration
public class WebSocketConfiguration {
    //ServerEndpointExporter核心作用自动扫描并注册所有使用了注解的类，将它们变为有效的 WebSocket 服务端点（Endpoint）
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
