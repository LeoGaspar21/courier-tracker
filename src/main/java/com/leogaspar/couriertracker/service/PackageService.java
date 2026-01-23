package com.leogaspar.couriertracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.leogaspar.couriertracker.entity.Package;
import com.leogaspar.couriertracker.entity.PackageStatus;
import com.leogaspar.couriertracker.entity.TrackingEvent;
import com.leogaspar.couriertracker.repositories.PackageRepository;
import com.leogaspar.couriertracker.service.exception.BusinessException;
import com.leogaspar.couriertracker.service.exception.ObjectNotFoundException;


@Service
public class PackageService {
	
	private final PackageRepository repository;
	
	
	
	public PackageService(PackageRepository repository) {
		this.repository = repository;
	}
	
	 
	public Package createPackage(String recipientName, LocalDateTime expectedDeliveredDate) {
		
		String trackingCode = generateTrackingCode();
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
	
	private String generateTrackingCode() {
		return UUID.randomUUID().toString().substring(0, 10);
	}
	
	public List<Package> getAllPackages(){
		return repository.findAll();
	}
	
	public Package getPackageByTrackingCode(String strackingCode) {
		Package pkg = repository.findById(strackingCode).orElseThrow(() -> new ObjectNotFoundException("TrackingCode Not Found"));
		
		return pkg;
		
		
	}
	public List<TrackingEvent> getTrackingEvents(String trackingCode) {
	    Package pkg = repository.findById(trackingCode)
	            .orElseThrow(() -> new ObjectNotFoundException("TrackingCode Not Found"));

	    return pkg.getEvents();
	} 
	
	public Package updatePackage(String trackingCode, String recipientName,LocalDateTime expectedDeliveredDate) {
		Package updatePkg = repository.findById(trackingCode).orElseThrow(() -> new ObjectNotFoundException("TrackingCode Not Found"));
		
		updatePkg.setRecipientName(recipientName); 
		updatePkg.setExpectedDeliveryDate(expectedDeliveredDate);
		
		repository.save(updatePkg);
		
		return updatePkg;
		
	}
	
	public void deletePackage(String trackingCode) {
		Package pkg = repository.findById(trackingCode).orElseThrow(() -> new ObjectNotFoundException("TrackingCode Not Found"));
		
		if (pkg.getCurrentStatus() == PackageStatus.DELIVERED) {
			throw new BusinessException("Delivered packages can't be deleted");
		}
		
		repository.delete(pkg);			
		
	}
	
	
	
}
