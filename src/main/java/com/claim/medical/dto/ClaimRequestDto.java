package com.claim.medical.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimRequestDto {

	private Integer policyId;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Integer hospitalId;
	private String doctor;
	private Double claimAmount;
	private Integer userId;
}
