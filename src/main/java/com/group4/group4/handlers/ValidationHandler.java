package com.group4.group4.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.group4.group4.exceptions.InvalidGPTResponseException;
import com.group4.group4.exceptions.InvalidPokeApiResponseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	@SuppressWarnings("null")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<String> erros = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(e -> e.getField() + " - " + e.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ResponseEntity<Object>(erros, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleException(HttpServletRequest request, Exception ex){
	    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(InvalidGPTResponseException.class)
    public ResponseEntity<Object> handleInvalidGPTResponseException(HttpServletRequest request, InvalidGPTResponseException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(InvalidPokeApiResponseException.class)
    public ResponseEntity<Object> handleInvalidPokeApiResponseException(HttpServletRequest request, InvalidPokeApiResponseException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	
}