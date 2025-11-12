package com.rin.notificationservice.config;

import com.rin.notificationservice.ws.handler.CustomHandshakeHandler;
import com.rin.notificationservice.ws.interceptor.KeycloakHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUrl;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        log.info("Keycloak Issuer URL: {}", issuerUrl);

        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(new KeycloakHandshakeInterceptor(issuerUrl))
                .setHandshakeHandler(new CustomHandshakeHandler());
        log.info("✅ WebSocket endpoint /ws registered with SockJS");

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setUserDestinationPrefix("/user");
    }
}
