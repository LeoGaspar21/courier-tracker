package com.leogaspar.couriertracker.dto;

import java.time.LocalDateTime;

import com.leogaspar.couriertracker.entity.PackageStatus;

public class PackageResponseDTO {
	private String trackingCode;
	private String recipientName;
	private LocalDateTime expectedDeliveryDate;
	private PackageStatus currentStatus;
	
	public PackageResponseDTO() {
		
	}
	
	
	public PackageResponseDTO(String trackingCode, String recipientName, LocalDateTime expectedDeliveryDate,
			PackageStatus currentStatus) {
		super();
		this.trackingCode = trackingCode;
		this.recipientName = recipientName;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.currentStatus = currentStatus;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public LocalDateTime getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(LocalDateTime expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public PackageStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(PackageStatus currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	
	
	
}
