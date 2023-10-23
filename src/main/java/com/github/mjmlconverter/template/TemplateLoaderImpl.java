package com.github.mjmlconverter.template;

import com.github.mjmlconverter.exception.TemplateLoadException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of the TemplateLoader interface, responsible for loading templates from the classpath.
 */
@Service
@RequiredArgsConstructor
public class TemplateLoaderImpl implements TemplateLoader {

    // Spring's utility for loading resources.
    private final ResourceLoader resourceLoader;

    /**
     * Loads a template from the classpath by its name.
     *
     * @param templateName The name of the template to be loaded.
     * @return The content of the loaded template as a string.
     * @throws TemplateLoadException If there is an issue loading the template.
     */
    @Override
    public String loadTemplate(String templateName) {
        try (InputStream in = resourceLoader.getResource("classpath:templates/" + templateName).getInputStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TemplateLoadException("Failed to load template: " + templateName, e);
        }
    }
}
