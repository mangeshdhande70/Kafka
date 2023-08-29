package com.mangesh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mangesh.dao.ICustomerRepo;
import com.mangesh.kakfa.KafkaMessageRetriever;
import com.mangesh.model.Customer;


@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Value("${spring.kafka.template.default-topic}")
	private  String topic;
	
	
	@Autowired
	private KafkaMessageRetriever kafkaMessageRetriever;

	
	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;
	
	
	@Autowired
	private ICustomerRepo repo;
	
    private List<Customer> customersCunsumerListFromKakfa = new ArrayList<>();
	
	UUID uuid = UUID.randomUUID();
	

	@Override
	public Customer addCustomer(Customer customer) {
		Customer repsnseCustomer = repo.save(customer);
		kafkaTemplate.send(topic,uuid.toString(),repsnseCustomer );
		return repsnseCustomer ;
	}

	@Override
	public List<Customer> getAllCustomer() {
		System.out.println(customersCunsumerListFromKakfa);
		System.out.println("from kafka");
		return kafkaMessageRetriever.getRetriverCustomerData();
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
	        kafkaTemplate.send(topic,uuid.toString(),customerFromDatabase.get());
	        return ResponseEntity.status(HttpStatus.FOUND).body(customerFromDatabase.get());
	    }

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found with id " + custId);
	}
	
	
	
	@KafkaListener(topics = "customer" , groupId = "custList1" , containerFactory = "customerKafkaListenerContainerFactory")
	public void getCustomerListFromKakfa(Customer customer) {
			customersCunsumerListFromKakfa.add(customer);
//			return customersCunsumerListFromKakfa;
			
		}


}
