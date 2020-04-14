package com.demo.bankservice.service;

import com.demo.bankservice.dto.AccountDetailsDto;
import com.demo.bankservice.dto.FundTransferDto;

public interface BankService {
	public AccountDetailsDto getAllAccounts(String phoneNumber);
	public String transferFunds(FundTransferDto fundsTransferDto);
}
