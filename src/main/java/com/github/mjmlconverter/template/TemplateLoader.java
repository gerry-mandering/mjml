package com.github.mjmlconverter.template;

/**
 * Defines the contract for loading templates.
 */
public interface TemplateLoader {

    /**
     * Loads a template by its name.
     *
     * @param templateName The name of the template to be loaded.
     * @return The content of the loaded template as a string.
     */
    String loadTemplate(String templateName);
}
