package com.claim.medical.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalClaimResponseDto {

	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private LocalDate medicalClaimRequestDate;
	private Integer claimId;
	private String claimType;
	private Double claimAmount;
	private Double eligibilityAmount;
	private String claimStatus;

}
