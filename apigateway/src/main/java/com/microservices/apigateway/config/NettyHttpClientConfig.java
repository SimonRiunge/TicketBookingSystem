package com.microservices.apigateway.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.cloud.gateway.config.HttpClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

@Configuration
public class NettyHttpClientConfig {

    @Bean
    // This method customizes the underlying Netty HttpClient used by Spring Cloud Gateway.
    //It ensures the gateway doesn't hang forever when connecting to or waiting for downstream services.
    public HttpClientCustomizer customizer() { //receives and modifies the default Netty HttpClient
        return httpClient -> httpClient
        //Set the connection timeout to 2000ms (2 seconds). If the gateway can't establish a TCP connection to a service within 2 seconds, it will fail
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000) 
        //Set the total time to wait for a full HTTP response (after connection is made).
        //If the service takes more than 5 seconds to respond, the request is aborted with a timeout error.
        //Prevents slow backend services from hanging the gateway
            .responseTimeout(Duration.ofSeconds(5)) //
        //Below block adds custom handlers to the Netty connection after it's successfully connected.
        //These handlers manage how long the gateway will wait during data transfer (read/write operations).
            .doOnConnected(conn -> 
                conn.addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.SECONDS)) //Ensures the gateway won’t wait more than 5 seconds while reading the response body
                    .addHandlerLast(new WriteTimeoutHandler(5, TimeUnit.SECONDS)) //Ensures that write operations also timeout after 5 seconds
            );
    }
    
    @Bean
    public ReactiveWebServerFactory reactiveWebServerFactory() {
        return new NettyReactiveWebServerFactory();
    }
}
