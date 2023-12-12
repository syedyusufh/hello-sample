package com.external.lib;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import reactor.core.scheduler.Schedulers;

@ControllerAdvice
public class NimbusCustomAdvice extends RequestBodyAdviceAdapter {

	private final static Map<String, ReactiveJwtDecoder> DECODERS = new ConcurrentHashMap<>();

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return false;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {

		var token = "Bearer NotAValidToken";
		var issuer = "tokenIssuer";

		var jwtDecoder = DECODERS.computeIfAbsent(issuer, decoderFn());

		// @formatter:off
		jwtDecoder.decode(token)
				.subscribeOn(Schedulers.boundedElastic())
				.block();
		// @formatter:on

		return body;
	}

	private Function<String, ReactiveJwtDecoder> decoderFn() {

		return issuer -> NimbusReactiveJwtDecoder.withIssuerLocation(issuer).build();
	}

}
