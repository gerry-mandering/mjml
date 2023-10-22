package com.github.mjmlconverter.converter;

import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MjmlConverterTest {

    @Mock
    private MjmlConverter mjmlConverter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMjmlConversionSuccess() throws Exception {
        // Given
        String mjmlContent = new String(Files.readAllBytes(Paths.get("src/test/resources/mjmlSample.mjml")));
        MjmlRequest mjmlRequest = new MjmlRequest(mjmlContent);

        String expectedHtmlContent = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedHtml.html")));
        HtmlResponse expectedHtmlResponse = new HtmlResponse(expectedHtmlContent);

        when(mjmlConverter.convert(mjmlRequest)).thenReturn(expectedHtmlResponse);

        // When
        HtmlResponse result = mjmlConverter.convert(mjmlRequest);

        // Then
        assertEquals(expectedHtmlResponse.getHtml(), result.getHtml());
    }

}