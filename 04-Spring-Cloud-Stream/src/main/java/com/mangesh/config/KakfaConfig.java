package com.mangesh.config;

import java.util.function.Consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangesh.model.Customer;

@Configuration
public class KakfaConfig {
	
	 @Bean
	    public Consumer<KStream<String, Customer>> process() {

	        return input ->
	                input.foreach((key, value) -> {
	                    System.out.println("Key: " + key + " Value: " + value);
	                });
	    }

}
