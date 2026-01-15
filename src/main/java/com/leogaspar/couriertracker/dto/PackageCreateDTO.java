package com.leogaspar.couriertracker.dto;

import java.time.LocalDateTime;

public class PackageCreateDTO {

	private String recipientName;
	private LocalDateTime expectedDeliveryDate;

	public PackageCreateDTO(String recipientName, LocalDateTime expectedDeliveryDate) {
		super();
		this.recipientName = recipientName;
		this.expectedDeliveryDate = expectedDeliveryDate;
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

}
