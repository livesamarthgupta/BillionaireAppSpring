package com.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Import({DaoConfig.class, ServiceConfig.class, WebConfig.class})
public class AppConfig {
    public AppConfig() {
        super();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholder() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

}
