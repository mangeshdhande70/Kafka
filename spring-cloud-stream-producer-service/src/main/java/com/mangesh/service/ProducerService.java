package com.mangesh.service;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
	

    @Bean
    public Supplier<Integer> numberProducer() {
    	
    	return () -> 20;
    	
//        return () -> new SecureRandom().nextInt(1, 100);
    }

}
