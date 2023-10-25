package com.github.mjmlconverter.client;

import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface MjmlClient {

    @PostExchange(url = "/render")
    HtmlResponse render(@RequestBody MjmlRequest mjmlRequest);
}
