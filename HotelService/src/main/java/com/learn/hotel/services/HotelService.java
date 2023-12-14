package com.learn.hotel.services;

import java.util.List;

import com.learn.hotel.entities.Hotel;

public interface HotelService {

	//
	
	Hotel create(Hotel hotel);
	
	//	
	List<Hotel> getAll();
	
	//	
	Hotel getString(String id);
	
}
