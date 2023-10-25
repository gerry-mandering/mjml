package com.github.mjmlconverter.client;

import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * Client interface for interacting with the MJML rendering API.
 * This interface provides methods to convert MJML templates into responsive HTML.
 */
public interface MjmlClient {

    /**
     * Sends a request to render an MJML template into responsive HTML.
     *
     * @param mjmlRequest Contains the MJML content to be rendered.
     * @return HtmlResponse Contains the resulting HTML after rendering.
     */
    @PostExchange(url = "/render")
    HtmlResponse render(@RequestBody MjmlRequest mjmlRequest);
}
