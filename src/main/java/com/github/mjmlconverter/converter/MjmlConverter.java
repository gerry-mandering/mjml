package com.github.mjmlconverter.converter;

import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;

/**
 * Defines the contract for converting MJML to HTML.
 */
public interface MjmlConverter {

    /**
     * Converts the provided MJML request to its corresponding HTML representation.
     *
     * @param mjmlRequest The MJML content to be converted.
     * @return The converted HTML response.
     */
    HtmlResponse convert(MjmlRequest mjmlRequest);

}
