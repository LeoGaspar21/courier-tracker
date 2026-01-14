package com.leogaspar.couriertracker.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leogaspar.couriertracker.entity.Package;
import com.leogaspar.couriertracker.service.PackageService;

@RestController
@RequestMapping(value = "/packages")
public class PackageResource {
	
	private final PackageService service;
	
	
	public PackageResource(PackageService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Package> createPackage(@RequestBody Package pkg){
		Package newPackage = service.createPackage(pkg.getTrackingCode(), pkg.getRecipientName(), pkg.getExpectedDeliveryDate());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newPackage);
		
	}
	
	
		
}
