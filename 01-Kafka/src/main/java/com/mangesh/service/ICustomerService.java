package com.mangesh.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mangesh.model.Customer;



public interface ICustomerService {
	
	public  Customer addCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public ResponseEntity<?> getCustomerById(Long id);

}
