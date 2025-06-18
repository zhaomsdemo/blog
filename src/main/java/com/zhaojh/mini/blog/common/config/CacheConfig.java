package com.zhaojh.mini.blog.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to enable caching in the application.
 */
@Configuration
@EnableCaching
public class CacheConfig {
    // The @EnableCaching annotation triggers the Spring Framework's caching infrastructure
    // Using default SimpleCache provider as no specific cache implementation is configured
}