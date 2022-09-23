package com.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DaoConfig.class, ServiceConfig.class, WebConfig.class})
public class AppConfig {
    public AppConfig() {
        super();
    }
}
