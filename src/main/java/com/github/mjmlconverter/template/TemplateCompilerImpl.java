package com.github.mjmlconverter.template;

import com.github.mjmlconverter.dto.MjmlRequest;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * Service implementation of the TemplateCompiler interface.
 * This class compiles provided templates using Mustache templating engine and produces MJML requests.
 */
@Service
@RequiredArgsConstructor
public class TemplateCompilerImpl implements TemplateCompiler {

    // Responsible for loading templates from specified locations.
    private final TemplateLoader templateLoader;

    // Factory used to create and compile Mustache templates.
    private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();

    /**
     * Compiles the provided template with the given parameters and returns an MJML request.
     *
     * @param templateName The name of the template to be compiled.
     * @param params       Parameters to be used in the template.
     * @return Compiled MjmlRequest.
     * @throws IOException In case of any issues while reading or compiling the template.
     */
    public MjmlRequest compile(String templateName, Map<String, Object> params) throws IOException {
        String template = templateLoader.loadTemplate(templateName);
        Mustache mustache = mustacheFactory.compile(new StringReader(template), templateName);

        StringWriter stringWriter = new StringWriter();
        mustache.execute(stringWriter, params).flush();

        return new MjmlRequest(stringWriter.toString());
    }
}
