package com.hopital.reservation.consumers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignClientConfiguration {
	@Value("${gateway.service.user.name}")
	private String userName;
	
	@Value("${gateway.service.user.password}")
	private String password;
	
    @Bean
    BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(userName, password);
    }
}
