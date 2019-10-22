package com.claim.medical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.claim.medical.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(MedicalClaimException.class)
	public ResponseEntity<ResponseDto> mortgageExceptionHandler(MedicalClaimException ex, WebRequest request) {
		ResponseDto responseDto = new ResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
	}
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