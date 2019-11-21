package com.hcl.elearning.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCourseRequestdto implements Serializable{
	
	private static final long serialVersionUID = -3874479972109727097L;
	private Integer courseId;
	private Integer userId;
}
