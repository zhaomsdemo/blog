package com.zhaojh.mini.blog.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditingConfig {

    public AuditorAware<String> auditorAware() {
        return new SecurityAuditorAware();
    }
}
