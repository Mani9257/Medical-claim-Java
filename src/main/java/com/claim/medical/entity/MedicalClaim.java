package com.claim.medical.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="medical_claim")
@Getter
@Setter
@NoArgsConstructor
public class MedicalClaim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer claimId;
	private Integer policyId;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Integer hospitalId;
	private String doctor;
	private Double claimAmount;
	private Integer userId;
    @JsonFormat(pattern = "dd-MM-YYYY")
	private LocalDate claimRaisedDate;
	
	
	

}
