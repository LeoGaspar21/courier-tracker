package com.leogaspar.couriertracker.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PackageCreateDTO {
	
	@NotBlank(message = "Recipient must have a name")
	private String recipientName;
	
	@NotNull(message = "Can't be null")
	@Future(message = "Must be a future date")
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
