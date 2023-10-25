package com.github.mjmlconverter.config;

import com.github.mjmlconverter.client.MjmlClient;
import com.github.mjmlconverter.exception.MjmlConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;


/**
 * Configuration class responsible for setting up the MJML client with necessary configurations
 * to communicate with the MJML API.
 *
 * It integrates Spring's WebClient with the MJML service to render MJML templates into HTML.
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MjmlProperties.class)
public class MjmlClientConfig {

    private static final String MJML_API_ENDPOINT = "https://api.mjml.io/v1";
    private final MjmlProperties properties;

    /**
     * Creates and configures the MJML client bean for rendering MJML templates.
     * It initializes a WebClient with default headers, error handling, and the MJML API endpoint.
     *
     * @return The configured MjmlClient bean ready to communicate with the MJML API.
     */
    @Bean
    public MjmlClient mjmlClient() {
        WebClient client = WebClient.builder()
                                   .defaultHeaders(httpHeaders -> {
                                       httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                                       httpHeaders.setBasicAuth(properties.getAppId(), properties.getAppSecret());
                                   })
                                   .defaultStatusHandler(HttpStatusCode::isError, response ->
                                                                                          Mono.just(new MjmlConversionException("Error from MJML API: " + response.bodyToMono(String.class))))
                                   .baseUrl(MJML_API_ENDPOINT)
                                   .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client))
                                                  .build();
        return factory.createClient(MjmlClient.class);
    }
}
