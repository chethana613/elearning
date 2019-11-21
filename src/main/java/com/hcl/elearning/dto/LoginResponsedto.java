package com.hcl.elearning.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponsedto implements Serializable{
	
	private static final long serialVersionUID = -1528532304068331799L;
	private String message;
	private Integer statusCode;
}
