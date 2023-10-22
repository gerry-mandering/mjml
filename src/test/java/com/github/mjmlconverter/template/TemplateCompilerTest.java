package com.github.mjmlconverter.template;

import com.github.mjmlconverter.dto.MjmlRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TemplateCompilerTest {

    @Mock
    private TemplateLoader templateLoader;

    @InjectMocks
    private TemplateCompilerImpl templateCompiler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCompileTemplateSuccess() throws IOException {
        // Given
        String templateName = "sampleTemplate.mjml";

        Path pathTemplate = Paths.get("src/test/resources/mjmlCompileSample.mjml");
        String templateContent = new String(Files.readAllBytes(pathTemplate), StandardCharsets.UTF_8);

        when(templateLoader.loadTemplate(templateName)).thenReturn(templateContent);

        Map<String, Object> params = new HashMap<>();
        params.put("email", "example@example.com");
        params.put("url", "test.com");

        // When
        MjmlRequest result = templateCompiler.compile(templateName, params);

        // Load the expected result from mjmlCompileResult.mjml file
        Path pathResult = Paths.get("src/test/resources/mjmlCompileResult.mjml");
        String expectedResult = new String(Files.readAllBytes(pathResult), StandardCharsets.UTF_8);

        // Then
        assertEquals(expectedResult, result.getMjml());
    }

}