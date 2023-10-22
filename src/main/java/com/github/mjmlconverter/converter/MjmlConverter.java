package com.github.mjmlconverter.converter;

import com.github.mjmlconverter.dto.HtmlResponse;
import com.github.mjmlconverter.dto.MjmlRequest;

public interface MjmlConverter {

    HtmlResponse convert(MjmlRequest mjmlRequest);

}
