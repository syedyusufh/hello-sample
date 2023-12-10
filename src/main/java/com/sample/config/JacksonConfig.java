package com.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Configuration
public class JacksonConfig {

	@Bean
	public XmlMapper xmlMapper() {

		var jacksonXmlModule = new JacksonXmlModule();
		jacksonXmlModule.setDefaultUseWrapper(false);

		var xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		xmlMapper.registerModule(jacksonXmlModule);

		return xmlMapper;
	}

}
