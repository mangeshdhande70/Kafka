package com.mangesh.controller;

import java.util.List;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.model.User;

@RestController
public class KafkaConsumer {
	
	
//	private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	
	List<User> userListFromKafkaTopic = null;
	
	@StreamListener(Sink.INPUT)
    public void processMessage(@Payload List<User> user) {
        // Logic to process the received UserDTO object
//        System.out.println("Received user: " + user.getName() + ", " + user.getAge())  
        userListFromKafkaTopic = user;
        
    }
	
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<User>> getUser(){
		return new ResponseEntity<>(userListFromKafkaTopic , HttpStatus.OK);
	}
	
	
}
