package com.microservices.apigateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//To allow us to import the URLs excluded for authentication 
//Mostly the documentation URLs
@Component
@RequiredArgsConstructor
@Data
@ConfigurationProperties(prefix = "security.excluded")
public class ExcludedUrlsConfig {
private final List<String> urls; 
}
