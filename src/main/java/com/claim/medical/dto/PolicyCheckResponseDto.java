package com.claim.medical.dto;



import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolicyCheckResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
    private Integer userId;
    private String userName;
	private String statusMessage;
	private Integer statusCode;
	private String status;
	
}
