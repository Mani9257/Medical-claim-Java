package com.claim.medical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PolicyExpiredException.class)
	public ErrorResponseDev handleError(PolicyExpiredException ex) {
		ErrorResponseDev errorResponseDev = new ErrorResponseDev();
		errorResponseDev.setStatusCode(HttpStatus.OK.value());
		errorResponseDev.setStatusMessage(ex.getMessage());
		errorResponseDev.setStatus("Falure");

		return errorResponseDev;
	}

	@ExceptionHandler(PolicyNotFoundException.class)
	public ErrorResponseDev handleError(PolicyNotFoundException ex) {
		ErrorResponseDev errorResponseDev = new ErrorResponseDev();
		errorResponseDev.setStatusCode(HttpStatus.OK.value());
		errorResponseDev.setStatusMessage(ex.getMessage());
		errorResponseDev.setStatus("Failure");

		return errorResponseDev;
	}

}