package com.github.mjmlconverter.converter;

import com.github.mjmlconverter.client.MjmlClient;
import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;
import com.github.mjmlconverter.exception.MjmlConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the MjmlConverter interface, converting MJML to HTML using the MJML API.
 */
@Service
@RequiredArgsConstructor
public class MjmlConverterImpl implements MjmlConverter {

    private final MjmlClient mjmlClient;

    /**
     * Converts the provided MJML content to HTML.
     *
     * @param mjmlRequest MJML content to be converted.
     * @return HtmlResponse containing the converted HTML content.
     * @throws MjmlConversionException If there's any error during conversion.
     */
    @Override
    public HtmlResponse convert(MjmlRequest mjmlRequest) {
        return mjmlClient.render(mjmlRequest);
    }
}
