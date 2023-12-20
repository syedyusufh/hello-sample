package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.external.lib.NimbusCustomAdvice;

import reactor.core.publisher.Hooks;

@Import(NimbusCustomAdvice.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HelloSampleApplication {

	public static void main(String[] args) {

		Hooks.enableAutomaticContextPropagation();

		SpringApplication.run(HelloSampleApplication.class, args);
	}

}
