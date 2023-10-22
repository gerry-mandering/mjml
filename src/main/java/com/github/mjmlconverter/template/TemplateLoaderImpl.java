package com.github.mjmlconverter.template;

import com.github.mjmlconverter.exception.TemplateLoadException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class TemplateLoaderImpl implements TemplateLoader {

    private final ResourceLoader resourceLoader;

    @Override
    public String loadTemplate(String templateName) {
        try (InputStream in = resourceLoader.getResource("classpath:templates/" + templateName).getInputStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TemplateLoadException("Failed to load template: " + templateName, e);
        }
    }
}
