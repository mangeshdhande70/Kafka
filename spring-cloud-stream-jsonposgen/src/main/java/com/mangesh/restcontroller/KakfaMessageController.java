package com.mangesh.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.invoice.model.PosInvoice;
import com.mangesh.kakfa.service.KafkaProducerService;

@RestController
public class KakfaMessageController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@PostMapping(value = "/post")
	public String sendMessageToKakfa(@RequestBody PosInvoice invoice ) {
		kafkaProducerService.sendMessage(invoice);
		
		return String.format("Success Message for- Key: %s, Value: %s , topic: %s", invoice.getStoreID() , invoice);		
	}

}
