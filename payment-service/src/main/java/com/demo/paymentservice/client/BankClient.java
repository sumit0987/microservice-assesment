package com.demo.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.demo.paymentservice.dto.AccountDetailsDto;
import com.demo.paymentservice.dto.FundTransferDto;


@FeignClient(name="http://bank-service/bank")
public interface BankClient {

	@GetMapping("/{phoneNumber}")
	public AccountDetailsDto getAllAccounts(@PathVariable("phoneNumber") String phone);
	
	@PostMapping("/transferFunds")
	public String transferFunds(@RequestBody FundTransferDto fundsTransferDto);
}
