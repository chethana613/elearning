package com.hcl.elearning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.elearning.entity.Course;
import com.hcl.elearning.entity.User;
import com.hcl.elearning.entity.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
	
	Optional<UserCourse> findByUserIdAndCourseId(User user, Course course);
	List<UserCourse> findAllByUserId(User user);

}
