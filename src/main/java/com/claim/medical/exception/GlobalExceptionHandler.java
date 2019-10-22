package com.claim.medical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ResponseDto> adminNotFoundExceptionHandler(AdminNotFoundException ex) {
		ResponseDto error = new ResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}