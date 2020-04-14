package com.demo.bankservice.dto;

public class FundTransferDto {
	private String phoneNumberFrom;
	private String phoneNumberTo;
	private double transferAmount;
	
	public String getPhoneNumberFrom() {
		return phoneNumberFrom;
	}
	public void setPhoneNumberFrom(String phoneNumberFrom) {
		this.phoneNumberFrom = phoneNumberFrom;
	}
	public String getPhoneNumberTo() {
		return phoneNumberTo;
	}
	public void setPhoneNumberTo(String phoneNumberTo) {
		this.phoneNumberTo = phoneNumberTo;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	
	
}
