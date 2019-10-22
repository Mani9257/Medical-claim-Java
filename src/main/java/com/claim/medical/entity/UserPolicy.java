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
@Table(name="user_policy")
@Getter
@Setter
@NoArgsConstructor
public class UserPolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userPolicyId;
	private Integer userId;
	private Integer policyId;
	private Double eligibilityAmount;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate startDate;
	@JsonFormat(pattern = "MM-dd-yyyy")
	private LocalDate endDate;
	

}
