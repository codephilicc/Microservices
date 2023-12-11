package com.learn.user.service.services;

import java.util.List;

import com.learn.user.service.entities.User;
import com.learn.user.service.repositories.UserRepository;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	
	
}
