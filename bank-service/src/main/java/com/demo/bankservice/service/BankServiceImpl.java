package com.demo.bankservice.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bankservice.dto.AccountDetailsDto;
import com.demo.bankservice.dto.FundTransferDto;
import com.demo.bankservice.entity.BankAccountDetails;
import com.demo.bankservice.entity.CustomerDetails;
import com.demo.bankservice.entity.TransactionDetails;
import com.demo.bankservice.repository.BankAccountDetailsRepo;
import com.demo.bankservice.repository.CustomerDetailsRepo;
import com.demo.bankservice.repository.TransactionDetailsRepo;

@Service
public class BankServiceImpl implements BankService{

	
	@Autowired
	BankAccountDetailsRepo bankAccountDetailsRepo;
	
	@Autowired
	CustomerDetailsRepo customerDetailsRepo;
	
	@Autowired
	TransactionDetailsRepo transactionRepo;
	
	@Override
	public AccountDetailsDto getAllAccounts(String phoneNumber) {
		// TODO Auto-generated method stub
		BankAccountDetails accountdetails=null;
		AccountDetailsDto accDetailsDto= new AccountDetailsDto();
		CustomerDetails customerList = customerDetailsRepo.findCustomerDetailsByPhoneNumber(phoneNumber);
		if(Objects.isNull(customerList)) {
			throw new RuntimeException("Customer not found");
		}
		accountdetails = customerList.getBankAccountDetails();
		if(Objects.isNull(accountdetails)) {
			throw new RuntimeException("Account not found");
		}
		
		accDetailsDto.setAccountNumber(accountdetails.getAccountNumber());
		accDetailsDto.setAge(customerList.getAge());
		accDetailsDto.setBalanceAmount(accountdetails.getBalanceAmount());
		accDetailsDto.setCustomerId(customerList.getCustomerId());
		accDetailsDto.setName(customerList.getName());
		accDetailsDto.setPhoneNumber(customerList.getPhoneNumber());
		return accDetailsDto;
	}

	@Override
	@Transactional
	public String transferFunds(FundTransferDto fundsTransferDto) {
		// TODO Auto-generated method stub
		String phoneNumberFrom=null;
		String phoneNumberTo=null;
		double transferAmount=0.00;
		double accountBalance=0.00;
		if(Objects.isNull(fundsTransferDto)) {
			throw new RuntimeException("Bad request......");
		}
		phoneNumberFrom = fundsTransferDto.getPhoneNumberFrom();
		transferAmount = fundsTransferDto.getTransferAmount();
		phoneNumberTo=fundsTransferDto.getPhoneNumberTo();
		
		CustomerDetails depositor = customerDetailsRepo.findCustomerDetailsByPhoneNumber(phoneNumberFrom);
		CustomerDetails creditor = customerDetailsRepo.findCustomerDetailsByPhoneNumber(phoneNumberTo);
		
		if(Objects.isNull(depositor)||Objects.isNull(depositor)) {
			throw new RuntimeException("Depositor/Creditor invalid");
		}
		
		BankAccountDetails accDepositor = depositor.getBankAccountDetails();
		BankAccountDetails accCreditor = creditor.getBankAccountDetails();
		
		accountBalance = accDepositor.getBalanceAmount();
		if(accountBalance<transferAmount || depositor.getCustomerId()==creditor.getCustomerId()){
			throw new RuntimeException("Bad Request, Unsufficient funds/wrong payee");
		}
		double debitorCurrentBalance = accDepositor.getBalanceAmount(); 
		bankAccountDetailsRepo.updateAccountDetails(accDepositor.getAccountNumber(),debitorCurrentBalance-transferAmount);
		
		TransactionDetails fundsTransaction = new TransactionDetails();
		fundsTransaction.setTransactionDate(new Date());
		fundsTransaction.setTransactionAmount(transferAmount);
		fundsTransaction.setTransactionType("debit");
		fundsTransaction.setAccountNumber(accCreditor.getAccountNumber());

		//List<Transaction> transactionList = new ArrayList<Transaction>();
		
		transactionRepo.save(fundsTransaction);
		
		double creditorCurrentBalance = accCreditor.getBalanceAmount();
		bankAccountDetailsRepo.updateAccountDetails(accCreditor.getAccountNumber(),creditorCurrentBalance+transferAmount);
		
		TransactionDetails fundsTransaction1 = new TransactionDetails();
		fundsTransaction1.setTransactionDate(new Date());
		fundsTransaction1.setTransactionAmount(transferAmount);
		fundsTransaction1.setTransactionType("credit");
		fundsTransaction1.setAccountNumber(accDepositor.getAccountNumber());
		
		transactionRepo.save(fundsTransaction1);
		
		return "funds transferred";
	}


}
