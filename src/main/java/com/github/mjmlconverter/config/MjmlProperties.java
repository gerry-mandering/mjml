package com.github.mjmlconverter.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "mjml")
@Validated
@Data
public class MjmlProperties {

    @NotEmpty
    private String appId;

    @NotEmpty
    private String appSecret;
}
