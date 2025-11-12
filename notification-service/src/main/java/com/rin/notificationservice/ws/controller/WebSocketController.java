package com.rin.notificationservice.ws.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {
    @MessageMapping("/ping")
    public void handlePing(Principal principal) {
        if (principal != null) {
            log.info("Ping from user {}", principal.getName());
            // optional: wsService.broadcast("/topic/ping", principal.getName());
        }
    }
}
