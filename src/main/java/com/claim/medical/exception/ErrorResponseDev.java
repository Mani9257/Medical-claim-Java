package com.claim.medical.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDev {

	
	private int statusCode;
	private String statusMessage;
	private String status;
	

}
