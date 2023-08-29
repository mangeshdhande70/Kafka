package com.mangesh.kakfa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mangesh.invoice.model.PosInvoice;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class KafkaProducerService {
	
	@Value("${application.configs.topic.name}")
	private String TOPIC_NAME;
	
	@Autowired
	private KafkaTemplate<String, PosInvoice> kafkaTemplate;
	
	public void sendMessage(PosInvoice invoice) {
		log.info("Producing Message- Invoice", invoice );
		kafkaTemplate.send(TOPIC_NAME , invoice.getStoreID() , invoice);
		
	}

}
