package com.github.mjmlconverter.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Configuration properties for the MJML API.
 * This class binds properties from the application's configuration that have a prefix of 'mjml'.
 */
@ConfigurationProperties(prefix = "mjml")
@Validated
@Data
public class MjmlProperties {

    /**
     * Identifier for the MJML API. This should not be empty.
     */
    @NotEmpty
    private String appId;

    /**
     * Secret key for the MJML API. This should not be empty.
     */
    @NotEmpty
    private String appSecret;
}
