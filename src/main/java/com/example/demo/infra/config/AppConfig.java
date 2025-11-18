package com.example.demo.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        // Altere 'localhost' se a API Flask estiver rodando em outro servidor/IP
        return builder.baseUrl("http://localhost:5000").build();
    }
}