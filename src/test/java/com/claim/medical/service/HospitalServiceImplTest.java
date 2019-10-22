package com.claim.medical.service;

import static org.junit.Assert.assertEquals;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.HospitalDTO;
import com.claim.medical.dto.HospitalResponseDTO;
import com.claim.medical.entity.Hospital;
import com.claim.medical.repository.HospitalRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class HospitalServiceImplTest {

@Mock
HospitalRepository hospitalRepository;

@InjectMocks
HospitalServiceImpl hospitalServiceImpl;


HospitalResponseDTO hospitalResponseDTO = new HospitalResponseDTO();
List<HospitalDTO> hospitalDTOs=new ArrayList<>();
HospitalDTO hospitalDTO=new HospitalDTO();

List<HospitalResponseDTO> hospitalResponseDTOs = new ArrayList<>();
List<Hospital> hospitals=new ArrayList<>();
Hospital hospital=new Hospital();

@Before
public void setUp() {
	hospitalDTO.setHospitalId(1);
	hospitalDTO.setHospitalName("APPOLLO");
	
	hospitalResponseDTO.setHospitalDTOs(hospitalDTOs);
	hospitalResponseDTO.setMessage("SUCESS");
	hospitalResponseDTO.setStatusCode(HttpStatus.OK.value());

	hospitalResponseDTOs.add(hospitalResponseDTO);
	
	hospital.setHospitalId(1);
	hospital.setHospitalName("Ajith");
	hospitals.add(hospital);
	
}
@Test
public void testHospitals() {
	
	Mockito.when(hospitalRepository.findAll()).thenReturn(hospitals);

	List<HospitalResponseDTO> response = hospitalServiceImpl.hospitals();
	assertEquals(1, response.size());


}





}
