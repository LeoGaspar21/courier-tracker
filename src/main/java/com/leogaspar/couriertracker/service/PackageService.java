package com.leogaspar.couriertracker.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.leogaspar.couriertracker.entity.Package;
import com.leogaspar.couriertracker.entity.PackageStatus;
import com.leogaspar.couriertracker.entity.TrackingEvent;
import com.leogaspar.couriertracker.repositories.PackageRepository;
import com.leogaspar.couriertracker.service.exception.ObjectNotFoundException;


@Service
public class PackageService {
	
	private final PackageRepository repository;
	
	
	
	public PackageService(PackageRepository repository) {
		this.repository = repository;
	}
	
	 
	public Package createPackage(String trackingCode, String recipientName, LocalDateTime expectedDeliveredDate) {
		Package newPackage = new Package(trackingCode, recipientName, expectedDeliveredDate, PackageStatus.POSTED);
		
		repository.save(newPackage);
		
		return newPackage;
	
	}
	
	public TrackingEvent addTrackingEvent(String trackingCode, PackageStatus status, String local) {
		Package pkg = repository.findById(trackingCode)
				.orElseThrow(() -> new ObjectNotFoundException("TrackingCode Not Found"));
		
		TrackingEvent event = new TrackingEvent(status, local);
		
		pkg.getEvents().add(event);
		
		pkg.setCurrentStatus(status);
		repository.save(pkg);
		
		return event;
	}
	
	
	
}
