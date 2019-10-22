package com.claim.medical.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClaimRequestDto {

	private Integer policyId;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate admissionDate;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dischargeDate;
	private Integer hospitalId;
	private String doctor;
	private Double claimAmount;
	private Integer userId;
}
