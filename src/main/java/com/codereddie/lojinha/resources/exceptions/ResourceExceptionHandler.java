package com.codereddie.lojinha.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(
			ObjectNotFoundException error, 
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(
				HttpStatus.NOT_FOUND.value(),
				error.getMessage(),
				System.currentTimeMillis()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
}
