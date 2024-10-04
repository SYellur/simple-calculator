package com.syellur.simple.calculator.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@Generated
public class SimpleCalculatorRequestDto {

	@NonNull
	private Integer firstInteger;

	@NonNull
	private Integer secondInteger;

}
