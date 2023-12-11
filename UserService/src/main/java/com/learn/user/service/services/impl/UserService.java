package com.learn.user.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.user.service.entities.User;
import com.learn.user.service.exception.ResourceNotFoundexception;
import com.learn.user.service.repositories.UserRepository;


@Service
public class UserService implements com.learn.user.service.services.UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundexception("User with given id is not found on server !! : "+userId));
	}

}
