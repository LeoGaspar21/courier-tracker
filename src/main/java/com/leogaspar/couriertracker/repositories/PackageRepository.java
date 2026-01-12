package com.leogaspar.couriertracker.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.leogaspar.couriertracker.entity.Package;
import com.leogaspar.couriertracker.entity.PackageStatus;


public interface PackageRepository extends MongoRepository<Package, String> {
	List<Package> findByCurrentStatus(PackageStatus status);
	
	List<Package> findByExpectedDeliveryDateBefore(LocalDateTime date);
	
	List<Package> findByRecipientName(String recipientName);
}
