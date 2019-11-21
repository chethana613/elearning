package com.hcl.elearning.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestdto implements Serializable{
	
	private static final long serialVersionUID = 4915337894905023614L;
	private String userMail;
	private String pass;
}