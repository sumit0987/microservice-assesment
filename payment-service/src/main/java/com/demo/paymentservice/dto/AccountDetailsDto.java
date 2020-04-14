package com.demo.paymentservice.dto;

public class AccountDetailsDto {
	private Long customerId;
	private String name;
	private String phoneNumber;
	private int age;
	private Long accountNumber;
	private Double balanceAmount;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	
	public AccountDetailsDto() {
		
	}
	public AccountDetailsDto(Long customerId, String name, String phoneNumber, int age, Long accountNumber,
			Double balanceAmount) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.accountNumber = accountNumber;
		this.balanceAmount = balanceAmount;
	}
	
	
	
}

