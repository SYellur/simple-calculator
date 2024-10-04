package com.syellur.simple.calculator.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DivisionByZeroException.class)
	public ResponseEntity<Map<String, String>> handleDivisionByZero(DivisionByZeroException ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Division by zero is not allowed.");
		errorResponse.put("message", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", "Internal server error");
		errorResponse.put("message", ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
