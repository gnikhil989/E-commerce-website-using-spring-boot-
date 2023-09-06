package com.jsp.Ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
@ExceptionHandler(value = CustomException.class)
public final ResponseEntity<String> handleCustomException(CustomException customException){
	return new ResponseEntity<String>(customException.getMessage(), HttpStatus.BAD_REQUEST);
}
}
