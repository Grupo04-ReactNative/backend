package com.group4.group4.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PokeApiRestTemplateConfig {
    
    @Bean
    @Qualifier("pokeApiRestTemplate")
    public RestTemplate pokeApiRestTemplate() {
        return new RestTemplate();
    }
}
