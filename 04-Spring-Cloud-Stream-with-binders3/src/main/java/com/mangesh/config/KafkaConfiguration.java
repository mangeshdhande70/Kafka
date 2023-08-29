package com.mangesh.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangesh.model.Customer;

@Configuration
public class KafkaConfiguration {
	
	
//	@Bean
//	public Supplier<Customer> producer(Customer customer) {
//	    return () -> customer;
//	}
//
//	@Bean
//	public Consumer<Customer> consumer() {
//	    return message -> System.out.println("received " + message);
//	}


}
