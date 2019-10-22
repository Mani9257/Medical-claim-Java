package com.claim.medical.dto;



import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClaimDetailsResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
    private Integer claimId;
    private LocalDate claimRaisedDate;
    private String claimType;
    private Double claimAmount;
    private String claimFirstLevelStatus;
	private Integer approverId;
	private String claimSecondLevelStatus;
	private Integer seniorAppoverId;
    private String statusMessage;
	private Integer statusCode;
	private String status;
	
}
