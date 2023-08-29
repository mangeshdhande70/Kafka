//package com.mangesh.kafka.service;
//
//import org.apache.kafka.streams.kstream.KStream;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.stereotype.Service;
//
//import com.mangesh.bindings.IPosListenerBinding;
//import com.mangesh.utils.Notification;
//
//import lombok.extern.log4j.Log4j2;
//
//@Service
//@EnableBinding(IPosListenerBinding.class)
//@Log4j2
//public class KafkaNotificationListener {
//	
//	
//	@StreamListener("input-channel-notification")
//	public void process(KStream<String, Notification> input) {
//		input.foreach( (k,v) -> log.info(String.format("Key: %s, Value: %s",k,v)));
//	}
//
//}
