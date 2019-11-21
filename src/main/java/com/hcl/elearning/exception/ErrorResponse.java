package com.hcl.elearning.exception;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {
	String exceptionMessage;
	LocalDate date;
	public ErrorResponse() {
		
	}
	public ErrorResponse(String exceptionMessage,LocalDate date) {
		super();
		this.exceptionMessage=exceptionMessage;
		this.date=date;
		
	}

}
