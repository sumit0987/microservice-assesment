package com.demo.paymentservice.controller;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.paymentservice.client.BankClient;
import com.demo.paymentservice.dto.AccountDetailsDto;
import com.demo.paymentservice.dto.FundTransferDto;
import com.demo.paymentservice.entity.PaymentApiCustomer;
import com.demo.paymentservice.entity.PaymentApiTransaction;
import com.demo.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/paymentapi")
@Transactional
public class PaymentController {
	
	@Autowired
	BankClient bankClient;
	
	@Autowired
	PaymentService paymentService;

	@GetMapping("/{phoneNumber}")
	public PaymentApiCustomer getBankDetails(@PathVariable String phoneNumber) {
		AccountDetailsDto accDetails = bankClient.getAllAccounts(phoneNumber);
		if(Objects.isNull(accDetails)) {
			throw new RuntimeException("ACoount not found.....");
		}
		return paymentService.registerNewAccount(accDetails);
	}
	
	@PostMapping("/transferFunds")
	public String tansferMoney(@RequestBody FundTransferDto dto) {
		AccountDetailsDto accDetailsDebitor = bankClient.getAllAccounts(dto.getPhoneNumberFrom());
		AccountDetailsDto accDetailsCreditor = bankClient.getAllAccounts(dto.getPhoneNumberTo());
		bankClient.transferFunds(dto);
		System.out.println("fundsTransferred....................");
		List<PaymentApiTransaction> transactionList = paymentService.saveTransactionHistory(dto,accDetailsDebitor,accDetailsCreditor);
		if(Objects.isNull(transactionList)||transactionList.size()==0) {
			throw new RuntimeException("Transaction could not complete payments service");
		}
			
		return "Money transferred successfully through gateway";
	}
	
	@GetMapping("/history/{phoneNumber}")
	public List<PaymentApiTransaction> findHistory(@PathVariable String phoneNumber){
		return paymentService.getTransactionHistory(phoneNumber);
	}
	
	
	
}
