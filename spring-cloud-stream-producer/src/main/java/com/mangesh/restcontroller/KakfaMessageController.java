package com.mangesh.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.kakfa.service.KafkaProducerService;
import com.mangesh.util.IncomingMessage;

@RestController
public class KakfaMessageController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@PostMapping(value = "/post")
	public String sendMessageToKakfa(@RequestBody IncomingMessage message ) {
		kafkaProducerService.sendMessage(message.getTopic(), message.getKey(), message.getValue());
		
		return String.format("Success Message for- Key: %s, Value: %s , topic: %s", message.getKey(),message.getValue(),message.getTopic());		
	}

}
