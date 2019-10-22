
package com.claim.medical.controller;

import static com.claim.medical.util.MedicalClaimConstants.ERROR_CODE;
import static com.claim.medical.util.MedicalClaimConstants.ERROR_MESSAGE;
import static com.claim.medical.util.MedicalClaimConstants.FAILURE_STATUS;
import static com.claim.medical.util.MedicalClaimConstants.SUCCESS_CODE;
import static com.claim.medical.util.MedicalClaimConstants.SUCCESS_MESSAGE;
import static com.claim.medical.util.MedicalClaimConstants.SUCCESS_STATUS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.LoginRequestDto;
import com.claim.medical.dto.LoginResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.service.LoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * this controller will return login details whether logged in credentials are
 * valid or not
 * 
 * @author Abhishek
 *
 */
@RestController
@RequestMapping("/mediclaim/api")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
@Slf4j
public class LoginController {

	/**
	 * this loginService will communicate with loginServiceRepository
	 */
	@Autowired
	private LoginService loginService;

	/**
	 * this api is used for login credentials
	 * 
	 * @param loginRequestDto
	 * @return LoginResponseDto
	 */
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<LoginResponseDto> doLogin(@RequestBody LoginRequestDto loginRequestDto) {

		String userName = loginRequestDto.getAdminName();
		String password = loginRequestDto.getPassword();
		Admin admin = loginService.login(userName, password);
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		log.info("message{} ", admin);
		if (admin != null) {
			loginResponseDto.setAdminId(admin.getAdminId());
			loginResponseDto.setAdminName(admin.getAdminName());
			loginResponseDto.setStatusCode(SUCCESS_CODE);
			loginResponseDto.setMessage(SUCCESS_MESSAGE);
			loginResponseDto.setStatusMsg(SUCCESS_STATUS);

			return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
		} else {
			loginResponseDto.setStatusCode(ERROR_CODE);
			loginResponseDto.setMessage(ERROR_MESSAGE);
			loginResponseDto.setStatusMsg(FAILURE_STATUS);

			log.info("loginResponseDto{} ", loginResponseDto);
			return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
		}
	}

}
