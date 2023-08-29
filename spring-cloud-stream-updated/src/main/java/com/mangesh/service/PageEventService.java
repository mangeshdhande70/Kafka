package com.mangesh.service;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mangesh.entities.PageEvent;

@Service
public class PageEventService {
	
	@Bean
	public Consumer<PageEvent> pageEventConsumer(){
		
		return (input)->{
			System.out.println("************");
			System.out.println(input.toString());
			System.out.println("************");
		};
		
	}

}
