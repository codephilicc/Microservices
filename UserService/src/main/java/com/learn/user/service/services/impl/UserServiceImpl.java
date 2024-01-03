package com.learn.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.user.service.entities.Rating;
import com.learn.user.service.entities.User;
import com.learn.user.service.exception.ResourceNotFoundexception;
import com.learn.user.service.repositories.UserRepository;
import com.learn.user.service.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//generate unique userid		
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		//get user from database with the help of user repository		
		User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundexception("User with given id is not found on server !! : "+userId));
		//fetch rating of the above user from RATING SERVICE		
		//http://localhost:8083/ratings/users/86fd4b90-0e6b-4c14-885b-3fb4011a9428		
		
		ArrayList<Rating> ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
		logger.info("{}" ,  ratingsOfUser);
		
		List<Rating> ratingList = ratingsOfUser.stream().map(rating ->{
			//api call to hotel service to get the hotel
			
			
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingsOfUser);
		
		return user;
	}

}
