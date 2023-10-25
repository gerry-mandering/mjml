package com.github.mjmlconverter.config;

import com.github.mjmlconverter.client.MjmlClient;
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

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MjmlProperties.class)
public class MjmlClientConfig {

    private final MjmlProperties properties;

    private static final String MJML_API_ENDPOINT = "https://api.mjml.io/v1";

    @Bean
    public MjmlClient mjmlClient() {
        WebClient client = WebClient.builder()
                                   .defaultHeaders(httpHeaders -> {
                                       httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                                       httpHeaders.setBasicAuth(properties.getAppId(), properties.getAppSecret());
                                   })
                                   .defaultStatusHandler(HttpStatusCode::isError, response ->
                                                                                          Mono.just(new RuntimeException("Error from MJML API: " + response.bodyToMono(String.class))))
                                   .baseUrl(MJML_API_ENDPOINT)
                                   .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client))
                                                  .build();
        return factory.createClient(MjmlClient.class);
    }
}
