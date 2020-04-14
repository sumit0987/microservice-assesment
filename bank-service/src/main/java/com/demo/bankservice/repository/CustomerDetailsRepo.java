package com.demo.bankservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.bankservice.entity.CustomerDetails;

@Repository
public interface CustomerDetailsRepo extends CrudRepository<CustomerDetails, Long> {
	
	public CustomerDetails findCustomerDetailsByPhoneNumber(String phoneNumber);
}
