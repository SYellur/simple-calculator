package com.syellur.simple.calculator.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syellur.simple.calculator.backend.dto.SimpleCalculatorRequestDto;
import com.syellur.simple.calculator.backend.dto.SimpleCalculatorResponseDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SimpleCalculatorController.class)
public class SimpleCalculatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private String jsonRequest;

	public SimpleCalculatorControllerTest() {
		new SimpleCalculatorController();
	}

	@BeforeEach
	public void setUp() throws JsonProcessingException {
		jsonRequest = objectMapper
			.writeValueAsString(SimpleCalculatorRequestDto.builder().firstInteger(5).secondInteger(3).build());

	}

	@Test
	public void simpleCalculatorController_testOnAddition() throws Exception {
		SimpleCalculatorResponseDto expectedResponse = new SimpleCalculatorResponseDto(8, "Addition");

		mockMvc.perform(post("/calculate/v1/add").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value(expectedResponse.getResult()))
			.andExpect(jsonPath("$.operation").value(expectedResponse.getOperation()));

	}

	@Test
	public void simpleCalculatorController_testOnSubtraction() throws Exception {
		SimpleCalculatorResponseDto expectedResponse = new SimpleCalculatorResponseDto(2, "Subtraction");
		mockMvc.perform(post("/calculate/v1/subtract").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value(expectedResponse.getResult()))
			.andExpect(jsonPath("$.operation").value(expectedResponse.getOperation()));
	}

	@Test
	public void simpleCalculatorController_testOnMultiplication() throws Exception {
		SimpleCalculatorResponseDto expectedResponse = new SimpleCalculatorResponseDto(15, "Multiplication");
		mockMvc.perform(post("/calculate/v1/multiply").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value(expectedResponse.getResult()))
			.andExpect(jsonPath("$.operation").value(expectedResponse.getOperation()));

	}

	@Test
	public void simpleCalculatorController_testOnDivision() throws Exception {
		SimpleCalculatorResponseDto expectedResponse = new SimpleCalculatorResponseDto(1, "Division");
		mockMvc.perform(post("/calculate/v1/divide").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result").value(expectedResponse.getResult()))
			.andExpect(jsonPath("$.operation").value(expectedResponse.getOperation()));

	}

	@Test
	public void simpleCalculatorController_testOnDivisionByZero() throws Exception {
		SimpleCalculatorRequestDto request = SimpleCalculatorRequestDto.builder()
			.firstInteger(5)
			.secondInteger(0)
			.build();
		jsonRequest = objectMapper.writeValueAsString(request);
		mockMvc.perform(post("/calculate/v1/divide").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.error").value("Division by zero is not allowed."))
			.andExpect(jsonPath("$.message").exists());
	}

	@Test
	public void simpleCalculatorController_testOnInvalidRequest() throws Exception {
		SimpleCalculatorRequestDto request = SimpleCalculatorRequestDto.builder()
			.firstInteger(Integer.MAX_VALUE)
			.secondInteger(Integer.MAX_VALUE)
			.build();
		jsonRequest = objectMapper.writeValueAsString(request);
		mockMvc.perform(post("/calculate/v1/mod").contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.error").value("Internal server error"))
			.andExpect(jsonPath("$.message").exists());
	}

}
