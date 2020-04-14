package com.demo.bankservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="bank_account_details")
public class BankAccountDetails {
	
	@Id
	private Long accountNumber;
	private Double balanceAmount;
	private String accountType;
	private String status;
	
	@OneToOne(mappedBy="bankAccountDetails")
	private CustomerDetails customerDetails;
	
//	@OneToMany(mappedBy="accountDetails")
//	private List<Transaction> transactionList;
//	
//	public List<Transaction> getTransactionList() {
//		return transactionList;
//	}
//
//	public void setTransactionList(List<Transaction> transactionList) {
//		this.transactionList = transactionList;
//	}


	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BankAccountDetails() {
		
	}

	public BankAccountDetails(Long accountNumber, Double balanceAmount, String accountType, String status,
			CustomerDetails customerDetails) {
		super();
		this.accountNumber = accountNumber;
		this.balanceAmount = balanceAmount;
		this.accountType = accountType;
		this.status = status;
		this.customerDetails = customerDetails;
	}
	
	
	
}
