package com.learn.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

//	we can implement any custom method or query.
	
}
