package com.sample.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MimeType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class JsonResponseConfig implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		converters.removeIf(converter -> supportsXml(converter) || hasXmlMapper(converter));
	}

	private boolean supportsXml(HttpMessageConverter<?> converter) {

		// @formatter:off
		return converter.getSupportedMediaTypes()
						.stream()
						.map(MimeType::getSubtype)
						.anyMatch("xml"::equalsIgnoreCase);
		// @formatter:on

	}

	private boolean hasXmlMapper(HttpMessageConverter<?> converter) {

		return converter instanceof MappingJackson2HttpMessageConverter jacksonConv
				&& jacksonConv.getObjectMapper().getClass().equals(XmlMapper.class);
	}

}
