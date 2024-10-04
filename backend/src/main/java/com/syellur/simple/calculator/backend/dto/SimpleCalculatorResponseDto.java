package com.syellur.simple.calculator.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
@Generated
public class SimpleCalculatorResponseDto {

	@NonNull
	private Integer result;

	@NonNull
	private String operation;

}