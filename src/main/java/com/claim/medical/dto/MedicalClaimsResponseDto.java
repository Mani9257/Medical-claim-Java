package com.claim.medical.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class MedicalClaimsResponseDto {
	private Integer claimId;
	private String policyName;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate admissionDate;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate dischargeDate;
	private String doctor;
	private Double claimAmount;
	private Double eligibleAmount;
	private String userName;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate claimRaisedDate;
	private Integer userId;
}
