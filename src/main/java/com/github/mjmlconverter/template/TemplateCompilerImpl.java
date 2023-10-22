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

@Service
@RequiredArgsConstructor
public class TemplateCompilerImpl implements TemplateCompiler {

    private final TemplateLoader templateLoader;
    private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();

    public MjmlRequest compile(String templateName, Map<String, Object> params) throws IOException {
        String template = templateLoader.loadTemplate(templateName);
        Mustache mustache = mustacheFactory.compile(new StringReader(template), templateName);

        StringWriter stringWriter = new StringWriter();
        mustache.execute(stringWriter, params).flush();

        return new MjmlRequest(stringWriter.toString());
    }
}
