package com.claim.medical.controller;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.claim.medical.dto.PolicyCheckResponseDto;
import com.claim.medical.entity.User;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.service.PolicyCheckService;


@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimCheckControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	ClaimCheckController claimCheckController;

	@Mock
	PolicyCheckService policyCheckService;
	
	
	

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(claimCheckController).build();
	}

	@Test
	public void testCheckPolicy() throws Exception {

		{
			mockMvc.perform(MockMvcRequestBuilders.get("/api/policies/{policyId}",2)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		}
		

	}
	
	@Test
	public void testviewClaimStatus() throws Exception {

		{
			mockMvc.perform(MockMvcRequestBuilders.get("/api/claims/policies/{policyId}",2)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		}
		

	}

}
