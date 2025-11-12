package com.rin.notificationservice.ws.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
public class KeycloakHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtDecoder jwtDecoder;

    public KeycloakHandshakeInterceptor(String issuerUri) {
        this.jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri);
    }


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if(request instanceof ServletServerHttpRequest serverRequest){
            String authHeader = serverRequest.getServletRequest().getHeader("Authorization");
            if(authHeader != null && authHeader.startsWith("Bearer ")) {
                try {
                    String token = authHeader.substring(7);
                    var jwt = jwtDecoder.decode(token);
                    var userId = jwt.getSubject();
                    attributes.put("userId", userId);
                    return true;
                } catch (Exception e) {
                    log.error("Invalid token: {}", e.getMessage());
                    return false; // Reject the handshake if token is invalid
                }
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
