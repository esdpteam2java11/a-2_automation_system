package com.a2.a2_automation_system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "file-store")
    public class FileStoreConfiguration {
        private String path;
        Authentication authentication;
    }
