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

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

	public List<HospitalResponseDTO> hospitals() {

		List<HospitalResponseDTO> responsehospitalList = new ArrayList<>();
		List<HospitalDTO> hospitalDTOs = new ArrayList<>();
		HospitalDTO hospitalDTO = new HospitalDTO();
		HospitalResponseDTO hospitalResponseDTO = new HospitalResponseDTO();

		List<Hospital> hospitals = hospitalRepository.findAll();
		if (!hospitals.isEmpty()) {
			hospitals.stream().forEach(hospital -> {

				hospitalDTO.setHospitalId(hospital.getHospitalId());
				hospitalDTO.setHospitalName(hospital.getHospitalName());
				hospitalDTOs.add(hospitalDTO);
				hospitalResponseDTO.setHospitalDTOs(hospitalDTOs);

			});

			hospitalResponseDTO.setMessage(MedicalClaimConstants.SUCCESSFUL_MESSAGE);
			hospitalResponseDTO.setStatusCode(MedicalClaimConstants.STATUS_CODE_FOR_OK);
			responsehospitalList.add(hospitalResponseDTO);
		}
		return responsehospitalList;

	}

}
