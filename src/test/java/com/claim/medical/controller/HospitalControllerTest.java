package com.claim.medical.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.HospitalDTO;
import com.claim.medical.dto.HospitalResponseDTO;
import com.claim.medical.service.HospitalService;

@RunWith(SpringJUnit4ClassRunner.class)
public class HospitalControllerTest {

	@InjectMocks
	private HospitalController hospitalController;

	@Mock
	private HospitalService hospitalService;

	HospitalResponseDTO hospitalResponseDTO = new HospitalResponseDTO();
	List<HospitalDTO> hospitalDTOs = new ArrayList<>();
	HospitalDTO hospitalDTO = new HospitalDTO();

	List<HospitalResponseDTO> hospitalResponseDTOs = new ArrayList<>();

	@Before
	public void setUp() {
		hospitalDTO.setHospitalId(1);
		hospitalDTO.setHospitalName("APPOLLO");

		hospitalResponseDTO.setHospitalDTOs(hospitalDTOs);
		hospitalResponseDTO.setMessage("SUCESS");
		hospitalResponseDTO.setStatusCode(HttpStatus.OK.value());

		hospitalResponseDTOs.add(hospitalResponseDTO);

	}

	@Test
	public void testHospitals() {

		Mockito.when(hospitalService.hospitals()).thenReturn(hospitalResponseDTOs);

		ResponseEntity<List<HospitalResponseDTO>> response = hospitalController.hospitals();

		assertEquals(200, response.getStatusCodeValue());

	}

}
