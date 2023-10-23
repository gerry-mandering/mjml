package com.github.mjmlconverter.builder;

import com.github.mjmlconverter.dto.MjmlRequest;
import com.github.mjmlconverter.template.TemplateCompiler;
import com.github.mjmlconverter.template.TemplateLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for constructing MjmlRequest objects from provided templates.
 * This builder takes into account both templates without any placeholders
 * and templates that require compilation with provided parameters.
 */
@Service
@RequiredArgsConstructor
public class MjmlRequestBuilder {

    private final TemplateCompiler templateCompiler;
    private final TemplateLoader templateLoader;

    /**
     * Builds an MJML request using the provided template name, assuming no parameters.
     *
     * @param templateName The name of the template.
     * @return An MJML request populated with the content of the template.
     * @throws IOException if there's an issue loading the template.
     */
    public MjmlRequest build(String templateName) throws IOException {
        return build(templateName, new HashMap<>());
    }

    /**
     * Builds an MJML request using the provided template name and parameters.
     * If parameters are provided, the template is compiled using them.
     *
     * @param templateName The name of the template.
     * @param params       Map of parameters to be used for template compilation.
     * @return An MJML request populated with the compiled template content.
     * @throws IOException if there's an issue loading or compiling the template.
     */
    public MjmlRequest build(String templateName, Map<String, Object> params) throws IOException {
        if (templateName == null || templateName.isEmpty()) {
            throw new IllegalArgumentException("Template name must be provided.");
        }

        if (params.isEmpty()) {
            String template = templateLoader.loadTemplate(templateName);
            return new MjmlRequest(template);
        } else {
            return templateCompiler.compile(templateName, params);
        }
    }
}
