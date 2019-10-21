package com.claim.medical.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolicyResponseDTO {

	private List<PolicyDTO> PolicyDtos;
	private String message;
	private Integer statusCode;
}
