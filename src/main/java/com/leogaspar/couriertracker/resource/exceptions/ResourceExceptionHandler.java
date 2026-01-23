package com.leogaspar.couriertracker.resource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.leogaspar.couriertracker.service.exception.BusinessException;
import com.leogaspar.couriertracker.service.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), e.getMessage(),"Tracking code not Found",
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<StandardError> business(BusinessException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), e.getMessage(),"Delivered packages can't be deleted",
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}

}
