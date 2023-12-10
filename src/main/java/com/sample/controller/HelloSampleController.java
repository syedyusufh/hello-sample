package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import reactor.core.publisher.Mono;

@RestController
public class HelloSampleController {
	
	@Autowired
	private XmlMapper xmlMapper;

	@GetMapping(path = "/greet")
	public Mono<String> greet() {
		
		return Mono.just("Have a Good One !!");
	}

}
