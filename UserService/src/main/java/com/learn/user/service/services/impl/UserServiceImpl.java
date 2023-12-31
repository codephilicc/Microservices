package com.learn.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn.user.service.entities.Hotel;
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
		// generate unique userid
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
		// get user from database with the help of user repository
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundexception("User with given id is not found on server !! : " + userId));
		// fetch rating of the above user from RATING SERVICE
		// http://localhost:8083/ratings/users/86fd4b90-0e6b-4c14-885b-3fb4011a9428

		Rating[] ratingsOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
		logger.info("{}", ratingsOfUser);
		
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/dcdeeb03-03d4-49e8-87d5-2da466789430

			ResponseEntity<Hotel> forEntity = restTemplate
					.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = forEntity.getBody();
			logger.info("{}", forEntity.getStatusCode());

			//set the hotel to rating			
			rating.setHotel(hotel);
			//return the rating			
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingList);

		return user;
	}

}
