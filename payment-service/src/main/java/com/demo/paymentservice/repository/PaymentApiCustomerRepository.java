package com.demo.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.paymentservice.entity.PaymentApiCustomer;

@Repository
public interface PaymentApiCustomerRepository extends CrudRepository<PaymentApiCustomer, Long> {
	
	public PaymentApiCustomer findByPhoneNumber(String phoneNumber);
}
