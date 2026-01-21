package com.leogaspar.couriertracker.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leogaspar.couriertracker.dto.PackageCreateDTO;
import com.leogaspar.couriertracker.dto.PackageResponseDTO;
import com.leogaspar.couriertracker.dto.TrackingEventDTO;
import com.leogaspar.couriertracker.dto.TrackingEventResponseDTO;
import com.leogaspar.couriertracker.entity.Package;
import com.leogaspar.couriertracker.entity.TrackingEvent;
import com.leogaspar.couriertracker.service.PackageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/packages")
public class PackageResource {

	private final PackageService service;

	public PackageResource(PackageService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<PackageResponseDTO> createPackage(@Valid @RequestBody PackageCreateDTO dto) {
		Package newPackage = service.createPackage(dto.getRecipientName(), dto.getExpectedDeliveryDate());
		PackageResponseDTO response = new PackageResponseDTO(newPackage.getTrackingCode(),
				newPackage.getRecipientName(), newPackage.getExpectedDeliveryDate(), newPackage.getCurrentStatus());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PostMapping(value = "/{trackingCode}/events")
	public ResponseEntity<TrackingEventResponseDTO> addTrackingEvent(@Valid @PathVariable String trackingCode,
			@RequestBody TrackingEventDTO tEDTO) {
		TrackingEvent newTE = service.addTrackingEvent(trackingCode, tEDTO.getStatus(), tEDTO.getLocation());
		TrackingEventResponseDTO response = new TrackingEventResponseDTO(newTE.getStatus(), newTE.getLocation(),
				newTE.getDate());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@GetMapping
	public ResponseEntity<List<PackageResponseDTO>> getAllPackages() {
		List<PackageResponseDTO> response = service.getAllPackages().stream()
				.map(pkg -> new PackageResponseDTO(pkg.getTrackingCode(), pkg.getRecipientName(),
						pkg.getExpectedDeliveryDate(), pkg.getCurrentStatus()))
				.toList();
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/{trackingCode}")
	public ResponseEntity<PackageResponseDTO> getPackageByTrackingCode(@PathVariable String trackingCode) {
		Package pkg = service.getPackageByTrackingCode(trackingCode);
		PackageResponseDTO response = new PackageResponseDTO(pkg.getTrackingCode(), pkg.getRecipientName(),
				pkg.getExpectedDeliveryDate(), pkg.getCurrentStatus());

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{trackingCode}/events")
	public ResponseEntity<List<TrackingEventResponseDTO>> getTrackingEvents(@PathVariable String trackingCode) {

		List<TrackingEventResponseDTO> response = service.getTrackingEvents(trackingCode).stream()
				.map(event -> new TrackingEventResponseDTO(event.getStatus(), event.getLocation(), event.getDate()))
				.toList();

		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{trackingCode}")
	public ResponseEntity<PackageResponseDTO> updatePackage(@PathVariable String trackingCode, @RequestBody PackageCreateDTO dto){
		Package updatePkg = service.updatePackage(trackingCode, dto.getRecipientName(), dto.getExpectedDeliveryDate());
		PackageResponseDTO response = new PackageResponseDTO(updatePkg.getTrackingCode(), updatePkg.getRecipientName(),
				updatePkg.getExpectedDeliveryDate(), updatePkg.getCurrentStatus());
		
		return ResponseEntity.ok().body(response);	
	}
	
	@DeleteMapping("/{trackingCode}")
	public ResponseEntity<Void> deletePackage(@PathVariable String trackingCode) {
		service.deletePackage(trackingCode);
		
		return ResponseEntity.noContent().build();
	}
	

}
