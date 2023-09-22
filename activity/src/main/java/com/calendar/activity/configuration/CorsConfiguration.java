package com.calendar.activity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class CorsConfiguration {
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        
        // Allowing all origins 
        config.addAllowedOrigin("*");
        
        // Allowing all HTTP methods 
        config.addAllowedMethod("*");
        
        
        config.addAllowedHeader("Origin");
        config.addAllowedHeader("Content-Type");
        config.addAllowedHeader("Accept");
        
       
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
