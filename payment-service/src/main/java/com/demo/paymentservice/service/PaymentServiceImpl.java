package com.demo.paymentservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.paymentservice.dto.AccountDetailsDto;
import com.demo.paymentservice.dto.FundTransferDto;
import com.demo.paymentservice.entity.PaymentApiCustomer;
import com.demo.paymentservice.entity.PaymentApiTransaction;
import com.demo.paymentservice.repository.PaymentApiCustomerRepository;
import com.demo.paymentservice.repository.PaymentApiTransactionRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentApiCustomerRepository paymentCustomerRepo;
	
	@Autowired
	PaymentApiTransactionRepository paymentTransactionRepo;

	@Override
	public PaymentApiCustomer registerNewAccount(AccountDetailsDto accDto) {
		// TODO Auto-generated method stub
		if(Objects.isNull(accDto)) {
			throw new RuntimeException("Error while registering with payment gateway...Bad Request");
		}
		
		PaymentApiCustomer paymentCustomer = paymentCustomerRepo.findByPhoneNumber(accDto.getPhoneNumber());
		
		if(Objects.isNull(paymentCustomer)) {
			PaymentApiCustomer customer = new PaymentApiCustomer();
			customer.setAccountNumber(accDto.getAccountNumber());
			customer.setName(accDto.getName());
			customer.setPhoneNumber(accDto.getPhoneNumber());
			paymentCustomerRepo.save(customer);
			return customer;
		}else {
			return paymentCustomer;
		}
		
		
	}

	@Override
	public List<PaymentApiTransaction> saveTransactionHistory(FundTransferDto fundsTransferDto, AccountDetailsDto accDetailsDebitor, AccountDetailsDto accDetailsCreditor) {
		// TODO Auto-generated method stub
		List<PaymentApiTransaction> transactionList = new ArrayList<PaymentApiTransaction>();
		
		PaymentApiCustomer customerTo = paymentCustomerRepo.findByPhoneNumber(fundsTransferDto.getPhoneNumberTo());
		PaymentApiCustomer customerFrom = paymentCustomerRepo.findByPhoneNumber(fundsTransferDto.getPhoneNumberFrom());
		
		PaymentApiTransaction transaction = new PaymentApiTransaction();
		transaction.setTransactionDate(new Date());
		transaction.setTransactionAmount(fundsTransferDto.getTransferAmount());
		transaction.setTransactionType("debit");
		transaction.setAccountNumber(accDetailsDebitor.getAccountNumber());
		
		paymentTransactionRepo.save(transaction);
		
		PaymentApiTransaction transaction1 = new PaymentApiTransaction();
		transaction1.setTransactionDate(new Date());
		transaction1.setTransactionAmount(fundsTransferDto.getTransferAmount());
		transaction1.setTransactionType("credit");
		transaction1.setAccountNumber(accDetailsCreditor.getAccountNumber());
		
		paymentTransactionRepo.save(transaction1);
		
		transactionList.add(transaction);
		transactionList.add(transaction1);
		
		return transactionList;
		
	}

	@Override
	public List<PaymentApiTransaction> getTransactionHistory(String phoneNumber) {
		// TODO Auto-generated method stub
		PaymentApiCustomer customer = paymentCustomerRepo.findByPhoneNumber(phoneNumber);
		if(Objects.isNull(customer)) {
			throw new RuntimeException("Transaction history does not exxist");
		}
		Long accountNumber = customer.getAccountNumber();
		return paymentTransactionRepo.findHistory(accountNumber);
	}

}
