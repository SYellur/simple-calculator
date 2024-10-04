package com.syellur.simple.calculator.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.syellur.simple.calculator.backend.dto.SimpleCalculatorRequestDto;
import com.syellur.simple.calculator.backend.dto.SimpleCalculatorResponseDto;
import com.syellur.simple.calculator.backend.exception.DivisionByZeroException;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Slf4j
@RequestMapping(value = "/calculate/", produces = MediaType.APPLICATION_JSON_VALUE)
@OpenAPIDefinition(
		info = @Info(title = "Simple Calculator API", version = "v1", description = "A simple calculator API",
				contact = @Contact(name = "Suhas Yellur", email = "suhas.yellur@hotmail.com")),
		servers = { @Server(description = "Local Server", url = "http://localhost:8081"), }

)
public class SimpleCalculatorController {

	@PostMapping(value = "v1/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Adds two integers", description = "Returns the sum of two integers", tags = { "v1" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful addition",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = SimpleCalculatorResponseDto.class),
							examples = @ExampleObject(value = "{ \"result\": 3, \"operation\": \"Addition\" }"))),
			@ApiResponse(responseCode = "400", description = "Invalid request data",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Invalid input data\" }"))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Internal server error\" }"))) })
	public SimpleCalculatorResponseDto add(@Validated @RequestBody SimpleCalculatorRequestDto request) {
		Integer result = request.getFirstInteger() + request.getSecondInteger();
		log.info("Adding: {} + {} = {}", request.getFirstInteger(), request.getSecondInteger(), result);
		return new SimpleCalculatorResponseDto(result, "Addition");
	}

	@PostMapping(value = "v1/subtract", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Subtract two integers", description = "Returns the difference of two integers",
			tags = { "v1" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful addition",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = SimpleCalculatorResponseDto.class),
							examples = @ExampleObject(value = "{ \"result\": 3, \"operation\": \"Subtraction\" }"))),
			@ApiResponse(responseCode = "400", description = "Invalid request data",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Invalid input data\" }"))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Internal server error\" }"))) })
	public SimpleCalculatorResponseDto subtract(@Validated @RequestBody SimpleCalculatorRequestDto request) {
		Integer result = request.getFirstInteger() - request.getSecondInteger();
		log.info("Subtracting: {} - {} = {}", request.getFirstInteger(), request.getSecondInteger(), result);
		return new SimpleCalculatorResponseDto(result, "Subtraction");
	}

	@PostMapping("v1/multiply")
	@Operation(summary = "Multiply two integers", description = "Returns the product of two integers", tags = { "v1" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful addition",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = SimpleCalculatorResponseDto.class),
							examples = @ExampleObject(value = "{ \"result\": 3, \"operation\": \"Multiplication\" }"))),
			@ApiResponse(responseCode = "400", description = "Invalid request data",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Invalid input data\" }"))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Internal server error\" }"))) })
	public SimpleCalculatorResponseDto multiply(@Validated @RequestBody SimpleCalculatorRequestDto request) {
		Integer result = request.getFirstInteger() * request.getSecondInteger();
		log.info("Multiplication: {} * {} = {}", request.getFirstInteger(), request.getSecondInteger(), result);
		return new SimpleCalculatorResponseDto(result, "Multiplication");
	}

	@PostMapping("v1/divide")
	@Operation(summary = "Devides two integers", description = "Returns the reminder of two integers", tags = { "v1" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Successful addition",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = SimpleCalculatorResponseDto.class),
							examples = @ExampleObject(value = "{ \"result\": 3, \"operation\": \"Division\" }"))),
			@ApiResponse(responseCode = "400", description = "Invalid request data",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Invalid input data\" }"))),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = @Content(mediaType = "application/json",
							examples = @ExampleObject(value = "{ \"error\": \"Internal server error\" }"))) })
	public SimpleCalculatorResponseDto divide(@Validated @RequestBody SimpleCalculatorRequestDto request) {
		if (request.getSecondInteger() == 0) {
			throw new DivisionByZeroException("Cannot divide by zero.");
		}
		Integer result = request.getFirstInteger() / request.getSecondInteger();
		log.info("Dividing: {} / {} = {}", request.getFirstInteger(), request.getSecondInteger(), result);
		return new SimpleCalculatorResponseDto(result, "Division");
	}

}