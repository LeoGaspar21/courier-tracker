package com.leogaspar.couriertracker.entity;

import java.time.LocalDateTime;

public class TrackingEvent {

	
	private PackageStatus status;
	private LocalDateTime date;
	private String location;
	
	public TrackingEvent() {
		
	}
	
	public TrackingEvent(PackageStatus status,  String location) {
		super();
		this.status = status;
		this.date = LocalDateTime.now();
		this.location = location;
	}

	public PackageStatus getStatus() {
		return status;
	}

	public void setStatus(PackageStatus status) {
		this.status = status;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	

	
	
}
