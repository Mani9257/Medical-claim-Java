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
@Table(name="claimstatus")
@Getter
@Setter
@NoArgsConstructor
public class ClaimStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer claimStatusId;
	private Integer claimId;
	private String claimFirstLevelStatus;
	private String approverId;
	private String claimSecondLevelStatus;
	private Integer seniorAppoverId;

}
