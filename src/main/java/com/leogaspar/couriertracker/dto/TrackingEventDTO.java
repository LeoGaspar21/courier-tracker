package com.leogaspar.couriertracker.dto;

import com.leogaspar.couriertracker.entity.PackageStatus;

public class TrackingEventDTO {
	
	private PackageStatus status;
	private String location;
	
	
	public TrackingEventDTO() {
		
	}


	public PackageStatus getStatus() {
		return status;
	}


	public void setStatus(PackageStatus status) {
		this.status = status;
	}



	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
}
