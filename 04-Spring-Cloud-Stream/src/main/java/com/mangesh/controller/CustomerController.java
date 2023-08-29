package com.mangesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.model.Customer;
import com.mangesh.service.ICustomerService;



@RestController
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
//	@Autowired
//	private KafkaMessageRetriever kafkaMessageRetriever;
	
	@PostMapping(value = "/post")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long id) {	
		return customerService.getCustomerById(id);
	}
	
	@GetMapping(value = "/getAll")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}
	
	

}
