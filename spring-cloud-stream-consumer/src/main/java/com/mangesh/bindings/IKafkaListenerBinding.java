package com.mangesh.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;



public interface IKafkaListenerBinding {
	
	@Input("input-channel-1")
	KStream<String, String> inputKStream();
	

}
