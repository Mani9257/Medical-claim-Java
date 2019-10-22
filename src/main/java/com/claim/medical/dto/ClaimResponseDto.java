package com.claim.medical.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimResponseDto {

	private Integer claimId;
	private String message;
	private Integer statusCode;
	
}
