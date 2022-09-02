package com.hooked.app.websocket.websocketconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
/*
@EnableWebSocketMessageBroker annotation enables broker implementation for web socket.
By default, spring uses in-memory broker using STOMP.
But Spring exposes easy way of replacing to RabbitMQ, ActiveMQ broker etc.
 */
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        /*
        All the websocket request must start with /websocket-chat i.e. [http://localhost:8080/websocket-chat/] (8080 is spring service port number)
         */
        stompEndpointRegistry.addEndpoint("/websocket-chat") // addEndpoint(“/websocket-chat”) – Register a STOMP over WebSocket endpoint at the given mapping path.
                /*
                You must add .setAllowedOrigins(“*”) when you are calling the client from different domain.
                That is, in practice, we keep two independent repository for client [UI – React js] and server [spring boot] and both are running in different port number.
                In this case, you must specify either .setAllowedOrigins(“http://localhost:{ui-port}/” or to be on safer side
                .setAllowedOrigins(“*”) which allows all the domain to connect to websocket.
                 */
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
        registry.enableSimpleBroker - Enable a simple message broker and configure one or more prefixes to filter
        destinations targeting the broker (e.g. destinations prefixed with "/topic").
        If you don't specify the relevant topic name, then client will fail to subscribe to given topic.
         */
        registry.enableSimpleBroker("/topic/");
        /*
        registry.setApplicationDestinationPrefixes("/app") - Configure one or more prefixes to filter destinations targeting application annotated methods.
        When messages are processed, the matching prefix is removed from the destination in order to form the lookup path.
        This means annotations should not contain the destination prefix.
         */
        registry.setApplicationDestinationPrefixes("/app");
    }
}
