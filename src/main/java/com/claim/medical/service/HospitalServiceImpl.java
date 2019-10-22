package com.claim.medical.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.HospitalDTO;
import com.claim.medical.dto.HospitalResponseDTO;
import com.claim.medical.entity.Hospital;
import com.claim.medical.repository.HospitalRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**
 * This Service Class is use to show all the Hospitals and it's details
 * 
 * @author Ajith
 *
 */

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

	/**
	 * This service is use to get all the available hospitals
	 * 
	 * @return List<HospitalResponseDTO>
	 */
	public List<HospitalResponseDTO> hospitals() {

		List<HospitalResponseDTO> responsehospitalList = new ArrayList<>();
		List<HospitalDTO> hospitalDTOs = new ArrayList<>();
	
		HospitalResponseDTO hospitalResponseDTO = new HospitalResponseDTO();
		hospitalResponseDTO.setMessage(MedicalClaimConstants.SUCCESSFUL_MESSAGE);
		hospitalResponseDTO.setStatusCode(MedicalClaimConstants.STATUS_CODE_FOR_OK);

		List<Hospital> hospitals = hospitalRepository.findAll();
		if (!hospitals.isEmpty()) {
			hospitals.stream().forEach(hospital -> {
				HospitalDTO hospitalDTO = new HospitalDTO();
				hospitalDTO.setHospitalId(hospital.getHospitalId());
				hospitalDTO.setHospitalName(hospital.getHospitalName());
				hospitalDTOs.add(hospitalDTO);
				hospitalResponseDTO.setHospitalDTOs(hospitalDTOs);
		
			});

			
			responsehospitalList.add(hospitalResponseDTO);
		}
		return responsehospitalList;

	}

}
