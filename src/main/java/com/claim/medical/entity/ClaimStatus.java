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
<<<<<<< HEAD
@Table(name = "claim_status")
=======
@Table(name="claimstatus")
>>>>>>> 29fd5d29ccfc16664b09e2e407fda06b670ea568
@Getter
@Setter
@NoArgsConstructor
public class ClaimStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer claimStatusId;
	private Integer claimId;
<<<<<<< HEAD
	private String firstLevelClaimStatus;
	private String secondLevelClaimStatus;
	private Integer aprroverId;
	private Integer seniorApproverId;

}
=======
	private String claimFirstLevelStatus;
	private String approverId;
	private String claimSecondLevelStatus;
	private Integer seniorAppoverId;

}
>>>>>>> 29fd5d29ccfc16664b09e2e407fda06b670ea568
