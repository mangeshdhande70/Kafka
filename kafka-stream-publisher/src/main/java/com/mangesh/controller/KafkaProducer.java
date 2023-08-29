package com.mangesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.model.User;

@RestController
public class KafkaProducer {
	
	
	@Value("${spring.kafka.template.default-topic}")	
    private String topic;
	
	
	   private StreamBridge streamBridge;

	    public KafkaProducer(StreamBridge streamBridge) {
	        this.streamBridge = streamBridge;
	    }
	    
	
	@PostMapping(value = "/publish")
	public List<User> publishEvent(@RequestBody List<User> user) {
		streamBridge.send(topic, user);
		return user;
	}
	
	

}
