package com.berktas.rentacar.core.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@CrossOrigin
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final ChannelInterceptorImpl channelInterceptor;
    private final HandshakeInterceptorImpl handshakeInterceptor;

    public WebSocketConfig(ChannelInterceptorImpl channelInterceptor,
                           HandshakeInterceptorImpl handshakeInterceptor) {
        this.channelInterceptor = channelInterceptor;
        this.handshakeInterceptor = handshakeInterceptor;
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor);
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                //.setAllowedOrigins("*")
                .setAllowedOriginPatterns("*")
                .addInterceptors(handshakeInterceptor)
                .withSockJS();
    }

}
