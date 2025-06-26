package com.microservices.apigateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

@Value("${keycloak.auth.jwk-set-uri}")
private String jwkSetUri;

private final ExcludedUrlsConfig excludedUrls;


@Bean
public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception{
	return httpSecurity.authorizeExchange(authorizeRequests->authorizeRequests
			.pathMatchers(excludedUrls.getUrls().toArray(new String[0])).permitAll()
			.anyExchange().authenticated())
			.oauth2ResourceServer(oauth->oauth.jwt(Customizer.withDefaults()))
			.build();
}

@Bean
public ReactiveJwtDecoder jwtDecoder() {
    return NimbusReactiveJwtDecoder.withJwkSetUri(jwkSetUri).build();
}

}
