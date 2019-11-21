package com.hcl.elearning.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseException extends Exception{
	
	private static final long serialVersionUID = -1629574187329732357L;

	public CourseException(String s) {
		super(s);
	}

}
