package com.mangesh.kakfa;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mangesh.config.KafkaConsumerConfig;
import com.mangesh.model.Customer;


@Component
public class KafkaMessageRetriever {
	
	
	@Autowired
	private KafkaConsumerConfig kafkaConsumerConfig;
	
	
	@Value("${spring.kafka.bootstrap-servers}") 
	private String bootStrapServer;
	
	
	@Value("${spring.kafka.template.default-topic}")
	private  String topic;
	
	
    public List<Customer> getRetriverCustomerData() {
    	
    	
    	Map<String, Object> consumerConfig = kafkaConsumerConfig.consumerConfig();
    	List<Customer> listOfCustomers = new ArrayList<>();
    	
        try (// Create a Kafka consumer
		KafkaConsumer<String, Customer> consumer = new KafkaConsumer<>(consumerConfig)) {
        	
////        	 For Signle Partition
////			 Assign the topic partitions to the consumer
//			TopicPartition topicPartition = new TopicPartition(topic, 0);
//			consumer.assign(Collections.singletonList(topicPartition));
//
//			// Seek to the beginning of the topic
//			consumer.seekToBeginning(Collections.singletonList(topicPartition));

        	
        	
			 // Assign multiple topic partitions to the consumer
	        List<TopicPartition> partitions = new ArrayList<>();
	        partitions.add(new TopicPartition(topic, 0));
	        partitions.add(new TopicPartition(topic, 1));
	        partitions.add(new TopicPartition(topic, 2));
	        partitions.add(new TopicPartition(topic, 3));
	        consumer.assign(partitions);

	        // Seek to the beginning of each partition
	        for (TopicPartition partition : partitions) {
	            consumer.seekToBeginning(Collections.singletonList(partition));
	        }

		
			// Start consuming message
			    ConsumerRecords<String, Customer> records = consumer.poll(Duration.ofMillis(100));
			    for (ConsumerRecord<String, Customer> record : records) {
//			        String key = record.key();
			        Customer value = record.value();
			        listOfCustomers.add(value);
//			        System.out.println("Received message: key = " + key + ", value = " + value);
			    }
			    
//			    System.out.println(listOfCustomers);

		}
        
        return listOfCustomers;
     
    }
    
    
}
