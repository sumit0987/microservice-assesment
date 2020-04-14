package com.demo.bankservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bankservice.dto.AccountDetailsDto;
import com.demo.bankservice.dto.FundTransferDto;
import com.demo.bankservice.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@GetMapping("/{phoneNumber}")
	public AccountDetailsDto getAllAccounts(@PathVariable String phoneNumber) {
		return bankService.getAllAccounts(phoneNumber);
	}
	
	@PostMapping("/transferFunds")
	public String transferFunds(@RequestBody FundTransferDto fundsTransferDto) {
		return bankService.transferFunds(fundsTransferDto);
	}
}
