package com.chc.rewardapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RewardAppExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleException(IllegalArgumentException e) {
		
		return new ResponseEntity<Object>("Illegal arg exp from Global",HttpStatus.BAD_REQUEST);
	}

}
