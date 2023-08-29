package com.mangesh.utils;

import lombok.Data;

@Data
public class Notification {
	
	private String invoiceNumber;
	
	private String customerCardNo;
	
	private Double totalAmount;
	
	private Double earnedLoyaltyPoints;
}
