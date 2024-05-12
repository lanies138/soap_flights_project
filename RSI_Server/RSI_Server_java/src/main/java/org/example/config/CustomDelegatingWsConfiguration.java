package org.example.config;

import java.util.List;

import org.example.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.DelegatingWsConfiguration;
import org.springframework.ws.server.EndpointInterceptor;

@Configuration
public class CustomDelegatingWsConfiguration extends DelegatingWsConfiguration {
    
    @Override
    protected void addInterceptors(List<EndpointInterceptor> interceptors) {
        super.addInterceptors(interceptors);
        interceptors.add(new LoggingInterceptor());
    }
}