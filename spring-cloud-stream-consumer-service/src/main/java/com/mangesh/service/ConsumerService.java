package com.mangesh.service;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {
	
	
	@Bean
	public Consumer<Integer> numberConsumer() {
		return incomingNumber -> log.info("Incoming Number : {}", incomingNumber);
	}

	@Bean
	public Function<Integer, Double> consumeAndProcessSqrt() {
		log.info("Squre Root");
		return Math::sqrt;
	}

	@Bean
	public Function<Integer, Double> consumeAndProcessCube() {
		log.info("Cube");
		return incomingNumber -> Math.pow(incomingNumber, 3);
	}
	
	@Bean
	public Consumer<Integer> sqrtConsumer() {
		return incomingNumber -> log.info("sqrt Number : {}", incomingNumber);
	}
	
	@Bean
	public Consumer<Double> consumeCube() {
		return incomingNumber -> log.info("cube Number : {}", incomingNumber);
	}
	
	

}
