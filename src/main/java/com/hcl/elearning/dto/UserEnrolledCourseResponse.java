package com.hcl.elearning.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEnrolledCourseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2822295115849724852L;
	private Integer userCourseId;
	private Integer courseId;
	private Integer userId;
	private LocalDate enrolledOn;
	private LocalDate expiryDate;
	private String courseStatus;

}
