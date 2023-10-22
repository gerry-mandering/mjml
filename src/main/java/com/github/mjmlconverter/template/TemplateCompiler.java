package com.github.mjmlconverter.template;

import com.github.mjmlconverter.dto.MjmlRequest;

import java.io.IOException;
import java.util.Map;

public interface TemplateCompiler {

    MjmlRequest compile(String templateName, Map<String, Object> params) throws IOException;
}
