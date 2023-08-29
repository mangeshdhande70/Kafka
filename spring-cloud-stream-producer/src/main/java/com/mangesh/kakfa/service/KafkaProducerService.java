package com.mangesh.kakfa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class KafkaProducerService {
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String topic , String key , String value) {
		
		log.info("Producing Message- Key: %s, Value: %s , topic: %s", key,value,topic );
		kafkaTemplate.send(topic , key , value);
		
	}

}
