package com.demo.paymentservice.service;

import java.util.List;

import com.demo.paymentservice.dto.AccountDetailsDto;
import com.demo.paymentservice.dto.FundTransferDto;
import com.demo.paymentservice.entity.PaymentApiCustomer;
import com.demo.paymentservice.entity.PaymentApiTransaction;

public interface PaymentService {
	public PaymentApiCustomer registerNewAccount(AccountDetailsDto accDto);
	public List<PaymentApiTransaction> saveTransactionHistory(FundTransferDto fundsTransferDto, AccountDetailsDto debitor, AccountDetailsDto creditor);
	public List<PaymentApiTransaction> getTransactionHistory(String phoneNumber);
}
