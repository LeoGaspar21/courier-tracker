package com.leogaspar.couriertracker.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leogaspar.couriertracker.entity.Package;
import com.leogaspar.couriertracker.entity.PackageStatus;
import com.leogaspar.couriertracker.repositories.PackageRepository;
import com.leogaspar.couriertracker.service.exception.BusinessException;
import com.leogaspar.couriertracker.service.exception.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

	@Mock
	private PackageRepository repository;

	@InjectMocks
	private PackageService service;

	@Test
	void shouldNotDeleteDeliveredPackage() {
		Package pkg = new Package("TRACK123", "João Batista", LocalDateTime.now(), PackageStatus.DELIVERED);
		
		
		Mockito.when(repository.findById("TRACK123")).thenReturn(Optional.of(pkg));
		
		assertThrows(BusinessException.class, () -> service.deletePackage("TRACK123"));
		
	}
	
	@Test
	void shouldThrowExceptionWhenPackageNotFount() {
		
		Mockito.when(repository.findById("ABC")).thenReturn(Optional.empty());
		
		assertThrows(ObjectNotFoundException.class, () -> service.deletePackage("ABC"));
		
		
	}
	
	@Test
	void shouldDeletePackageWhenStatusIsNotDelivered() {
		
		Package pkg = new Package("TRACK548", "Carlos Barbosa", LocalDateTime.now(), PackageStatus.IN_TRANSIT);
		
		Mockito.when(repository.findById("TRACK548")).thenReturn(Optional.of(pkg));
		
		assertDoesNotThrow(() -> service.deletePackage("TRACK548"));
		
		Mockito.verify(repository).delete(pkg);
		
		
	}
	

}
