package com.claim.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.HospitalResponseDTO;
import com.claim.medical.service.HospitalService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is used to get all the hospitals available
 * 
 * @author Ajith
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
@Slf4j
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	/**
	 * This method is use to get all the hospitals
	 * 
	 * @return List<HospitalResponseDTO>
	 */

	@GetMapping("/hospitals")
	public ResponseEntity<List<HospitalResponseDTO>> hospitals() {
		log.info("Inside Hospitals");

		List<HospitalResponseDTO> hospitals = hospitalService.hospitals();

		return new ResponseEntity<>(hospitals, HttpStatus.OK);

	}

}
