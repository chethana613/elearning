package com.hcl.elearning.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCourseResponsedto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3075153262107993942L;
	private String message;
	private Integer statusCode;
}
