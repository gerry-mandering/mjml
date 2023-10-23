package com.github.mjmlconverter.template;

import com.github.mjmlconverter.dto.MjmlRequest;

import java.io.IOException;
import java.util.Map;

/**
 * Interface defining the contract for template compilation.
 */
public interface TemplateCompiler {

    /**
     * Compiles the specified template using the given parameters and produces an MJML request.
     *
     * @param templateName Name of the template to be compiled.
     * @param params       Map containing parameters to be substituted in the template.
     * @return MjmlRequest containing the compiled MJML content.
     * @throws IOException If there's an error during template loading or compilation.
     */
    MjmlRequest compile(String templateName, Map<String, Object> params) throws IOException;
}
