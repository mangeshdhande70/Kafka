package com.mangesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.model.Customer;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {

}
