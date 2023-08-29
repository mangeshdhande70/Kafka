package com.mangesh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mangesh.dao.ICustomerRepo;
import com.mangesh.model.Customer;


@Service
public class CustomerServliceImpl implements ICustomerService,Consumer<KStream<String, Customer>>{

	
    @Autowired
	private StreamBridge streamBridge;
	
	@Autowired
	private ICustomerRepo repo;
	
    private List<Customer> customersCunsumerListFromKakfa = new ArrayList<>();
	
	UUID uuid = UUID.randomUUID();
	
	
	List<Customer> customers = new ArrayList<>();
	

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
		return customers;
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

	@Override
	public void accept(KStream<String, Customer> input) {
		
		 input.foreach((key, value) -> {
			 System.out.println("From Accept Method");
	            System.out.println("Key: " + key + " Value: " + value);
	            customers.add(value);
	            
	        });
	}
	
	
	
	
	
}
