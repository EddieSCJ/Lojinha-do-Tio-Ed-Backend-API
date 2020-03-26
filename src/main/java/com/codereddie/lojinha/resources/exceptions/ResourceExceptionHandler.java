package com.codereddie.lojinha.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.codereddie.lojinha.services.exceptions.AuthorizationException;
import com.codereddie.lojinha.services.exceptions.DataIntegrityException;
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
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrityException(
			DataIntegrityException error, 
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(
				HttpStatus.BAD_REQUEST.value(),
				error.getMessage(),
				System.currentTimeMillis()
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(
			MethodArgumentNotValidException error, 
			HttpServletRequest request){
		
		ValidationError validationError = new ValidationError(
				HttpStatus.BAD_REQUEST.value(),
				"Validation Erros ",
				System.currentTimeMillis()
				);
		
		for(FieldError e : error.getBindingResult().getFieldErrors()) {
			validationError.addMessagesList(e.getField(), e.getDefaultMessage()); 
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(
			ObjectNotFoundException error, 
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(
				HttpStatus.NOT_FOUND.value(),
				error.getMessage(),
				System.currentTimeMillis()
				);
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}

}
