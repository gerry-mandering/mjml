package com.github.mjmlconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request containing MJML content to be converted.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MjmlRequest {

    // The MJML content to be converted.
    private String mjml;

}
