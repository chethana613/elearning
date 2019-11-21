package com.hcl.elearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.elearning.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUserMailAndPass(String userMail,String pass);
}
