package com.demo.bankservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.bankservice.entity.TransactionDetails;

public interface TransactionDetailsRepo extends CrudRepository<TransactionDetails, Integer> {

}
