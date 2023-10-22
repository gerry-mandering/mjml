package com.github.mjmlconverter.config;

import com.github.mjmlconverter.builder.MjmlRequestBuilder;
import com.github.mjmlconverter.converter.MjmlConverter;
import com.github.mjmlconverter.converter.MjmlConverterImpl;
import com.github.mjmlconverter.template.TemplateCompiler;
import com.github.mjmlconverter.template.TemplateCompilerImpl;
import com.github.mjmlconverter.template.TemplateLoader;
import com.github.mjmlconverter.template.TemplateLoaderImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(MjmlProperties.class)
public class MjmlConverterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MjmlConverter mjmlConverter(MjmlProperties properties, RestTemplate restTemplate) {
        return new MjmlConverterImpl(properties, restTemplate);
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

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
