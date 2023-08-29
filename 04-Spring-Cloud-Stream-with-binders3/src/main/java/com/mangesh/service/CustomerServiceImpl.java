package com.mangesh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mangesh.dao.ICustomerRepo;
import com.mangesh.model.Customer;


@Service
public class CustomerServiceImpl implements ICustomerService{
	
//	@Value("${spring.kafka.template.default-topic}")
//	private  String topic;
	
     @Autowired
	 private StreamBridge streamBridge;
	
	
//	@Autowired
//	private KafkaTemplate<String, Customer> kafkaTemplate;
     
     
//     @Bean
// 	 public Supplier<Customer> producer(Customer customer) {
// 	    return () -> customer;
// 	 }
	
	
	@Autowired
	private ICustomerRepo repo;
	
    private List<Customer> customersCunsumerListFromKakfa = new ArrayList<>();
	
	UUID uuid = UUID.randomUUID();
	

	@Override
	public Customer addCustomer(Customer customer) {
		Customer respnseCustomer = repo.save(customer);
		streamBridge.send("producer-out-0",respnseCustomer);
		return respnseCustomer ;
	}

	@Override
	public List<Customer> getAllCustomer() {
		System.out.println(customersCunsumerListFromKakfa);
		System.out.println("from kafka");
//		return kafkaMessageRetriever.getRetriverCustomerData();
		return null;
	}

	@Override
	public ResponseEntity<?> getCustomerById(Long custId) {
	    Customer customer = null;
	
	    // Check if the customer is already present in the Kafka-consumed customer list
	    for (Customer customerFromKafka : customersCunsumerListFromKakfa) {
	        if (customerFromKafka.getCustId().equals(custId)) {
	            customer = customerFromKafka;
	            break;
	        }
	    }

	    if (customer != null) {
	        System.out.println("Response sent from Kafka");
	        return ResponseEntity.status(HttpStatus.FOUND).body(customer);
	    }

	    // If not found in the Kafka-consumed list, retrieve the customer from the database
	    Optional<Customer> customerFromDatabase = repo.findById(custId);
	    if (customerFromDatabase.isPresent()) {
	        System.out.println("Response from Database");
	        streamBridge.send("producer-out-0",uuid.toString(),customerFromDatabase.get());
	        return ResponseEntity.status(HttpStatus.FOUND).body(customerFromDatabase.get());
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found with id " + custId);
	}
	
	
	
//	@KafkaListener(topics = "customer" , groupId = "custList1" , containerFactory = "customerKafkaListenerContainerFactory")
//	public void getCustomerListFromKakfa(Customer customer) {
//			customersCunsumerListFromKakfa.add(customer);
////			return customersCunsumerListFromKakfa;
//			
//		}


}
