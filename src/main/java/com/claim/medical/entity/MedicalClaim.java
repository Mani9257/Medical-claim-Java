package com.claim.medical.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String claimStatus;
	private String dischargeSummaryUrl;
	private Integer userId;
	private LocalDate claimRaisedDate;
	@Override
	public String toString() {
		return "MedicalClaim [claimId=" + claimId + ", policyId=" + policyId + ", admissionDate=" + admissionDate
				+ ", dischargeDate=" + dischargeDate + ", hospitalId=" + hospitalId + ", doctor=" + doctor
				+ ", claimAmount=" + claimAmount + ", claimStatus=" + claimStatus + ", dischargeSummaryUrl="
				+ dischargeSummaryUrl + ", userId=" + userId + ", claimRaisedDate=" + claimRaisedDate + "]";
	}

	
	

}
