package com.leogaspar.couriertracker.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages_db")
public class Package implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String trackingCode;

	private String recipientName;
	private LocalDateTime expectedDeliveryDate;
	private PackageStatus currentStatus;

	private List<TrackingEvent> events = new ArrayList<>();

	public Package() {

	}

	public Package(String trackingCode, String recipientName, LocalDateTime expectedDeliveryDate,
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

	public PackageStatus getStatus() {
		return currentStatus;
	}

	public void setStatus(PackageStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public List<TrackingEvent> getEvents() {
		return events;
	}

	@Override
	public int hashCode() {
		return Objects.hash(trackingCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Package other = (Package) obj;
		return Objects.equals(trackingCode, other.trackingCode);
	}
	
	public void addEvent(TrackingEvent event) {
		this.events.add(event);
		this.currentStatus = event.getStatus();
	}

}
