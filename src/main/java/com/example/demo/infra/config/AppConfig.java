package com.example.demo.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${gemini.api.base-url}")
    private String geminiApiBaseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        // 2. Usa o endereço configurado externamente
        System.out.println("Configurando WebClient com URL Base: " + geminiApiBaseUrl);
        return builder.baseUrl(geminiApiBaseUrl).build();
    }
}