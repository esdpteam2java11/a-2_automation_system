package com.a2.a2_automation_system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "file-store")
public class FileStoreConfiguration {
    private String path;

}
