package com.claim.medical.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "claim_status")
@Getter
@Setter
@NoArgsConstructor
public class ClaimStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer claimStatusId;
	private Integer claimId;
	private String firstLevelClaimStatus;
	private String secondLevelClaimStatus;
	private Integer aprroverId;
	private Integer seniorApproverId;

}