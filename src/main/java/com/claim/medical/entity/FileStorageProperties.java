package com.claim.medical.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ConfigurationProperties(prefix = "file")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileStorageProperties {
    private String uploadDir;

}
