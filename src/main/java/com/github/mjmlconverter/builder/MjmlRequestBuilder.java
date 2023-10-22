package com.github.mjmlconverter.builder;

import com.github.mjmlconverter.dto.MjmlRequest;
import com.github.mjmlconverter.template.TemplateCompiler;
import com.github.mjmlconverter.template.TemplateLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MjmlRequestBuilder {

    private final TemplateCompiler templateCompiler;
    private final TemplateLoader templateLoader;

    public MjmlRequest build(String templateName) throws IOException {
        return build(templateName, new HashMap<>());
    }

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
