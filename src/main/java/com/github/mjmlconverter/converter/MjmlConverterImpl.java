package com.github.mjmlconverter.converter;

import com.github.mjmlconverter.config.MjmlProperties;
import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;
import com.github.mjmlconverter.exception.MjmlConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of the MjmlConverter interface, converting MJML to HTML using the MJML API.
 */
@Service
@RequiredArgsConstructor
public class MjmlConverterImpl implements MjmlConverter {

    // Endpoint URL for the MJML API.
    private static final String MJML_API_ENDPOINT = "https://api.mjml.io/v1/render";

    private final MjmlProperties properties;
    private final RestTemplate restTemplate;

    /**
     * Converts the provided MJML content to HTML.
     *
     * @param mjmlRequest MJML content to be converted.
     * @return HtmlResponse containing the converted HTML content.
     * @throws MjmlConversionException If there's any error during conversion.
     */
    @Override
    public HtmlResponse convert(MjmlRequest mjmlRequest) {
        HttpEntity<MjmlRequest> entity = createHttpEntity(mjmlRequest);

        // Attempt to send the MJML request and get the HTML response.
        try {
            return restTemplate.postForObject(MJML_API_ENDPOINT, entity, HtmlResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle 4xx/5xx errors.
            throw new MjmlConversionException("Error from MJML API: " + e.getResponseBodyAsString(), e);
        } catch (ResourceAccessException e) {
            // Handle connection errors.
            throw new MjmlConversionException("Unable to connect to MJML API.", e);
        } catch (HttpMessageNotReadableException e) {
            // Handle deserialization errors.
            throw new MjmlConversionException("Unexpected response from MJML API.", e);
        } catch (Exception e) {
            // Catch-all for other exceptions.
            throw new MjmlConversionException("Unknown error during MJML conversion.", e);
        }
    }

    /**
     * Prepares the HTTP entity for MJML API request with appropriate headers and body.
     *
     * @param mjmlRequest MJML content for the request body.
     * @return Configured HttpEntity instance.
     */
    private HttpEntity<MjmlRequest> createHttpEntity(MjmlRequest mjmlRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(properties.getAppId(), properties.getAppSecret());

        return new HttpEntity<MjmlRequest>(mjmlRequest, headers);
    }
}
