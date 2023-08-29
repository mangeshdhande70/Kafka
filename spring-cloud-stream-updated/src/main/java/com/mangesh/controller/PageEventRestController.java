package com.mangesh.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.entities.PageEvent;

@RestController
public class PageEventRestController {
	


	
	@Autowired
	private StreamBridge streamBridge;
	
	@GetMapping(value = "/publish/{topic}/{name}")
	public PageEvent publish(@PathVariable String name,@PathVariable String topic) {
		
		PageEvent pageEvent = new PageEvent(name, Math.random()>0.5?"U1":"U2", new Date(), new Random().nextLong(9000));
		streamBridge.send(topic,pageEvent);
		
		return pageEvent;
		
	}
	
	

	
}
