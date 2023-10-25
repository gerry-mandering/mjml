package com.github.mjmlconverter.config;

import com.github.mjmlconverter.builder.MjmlRequestBuilder;
import com.github.mjmlconverter.client.MjmlClient;
import com.github.mjmlconverter.converter.MjmlConverter;
import com.github.mjmlconverter.converter.MjmlConverterImpl;
import com.github.mjmlconverter.template.TemplateCompiler;
import com.github.mjmlconverter.template.TemplateCompilerImpl;
import com.github.mjmlconverter.template.TemplateLoader;
import com.github.mjmlconverter.template.TemplateLoaderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * Spring Boot auto-configuration class for the MJML converter components.
 * This class contains bean definitions that are conditionally created only if they don't already exist in the context.
 */
@Configuration
public class MjmlConverterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MjmlConverter mjmlConverter(MjmlClient mjmlClient) {
        return new MjmlConverterImpl(mjmlClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public MjmlRequestBuilder mjmlRequestBuilder(TemplateCompiler templateCompiler, TemplateLoader templateLoader) {
        return new MjmlRequestBuilder(templateCompiler, templateLoader);
    }

    @Bean
    @ConditionalOnMissingBean
    public TemplateCompiler templateCompiler(TemplateLoader templateLoader) {
        return new TemplateCompilerImpl(templateLoader);
    }

    @Bean
    @ConditionalOnMissingBean
    public TemplateLoader templateLoader(ResourceLoader resourceLoader) {
        return new TemplateLoaderImpl(resourceLoader);
    }
}
