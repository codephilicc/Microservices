package com.learn.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.hotel.entities.Hotel;
import com.learn.hotel.exception.ResourceNotFoundexception;
import com.learn.hotel.repositories.HotelRepository;
import com.learn.hotel.services.HotelService;



@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	public HotelRepository hotelRepositry;

	@Override
	public Hotel create(Hotel hotel) {
		
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return hotelRepositry.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		return hotelRepositry.findAll();
	}

	@Override
	public Hotel getString(String id) {
		return hotelRepositry.findById(id).orElseThrow(()-> new ResourceNotFoundexception("hotel with given id not found !! : "+id));
		
	}

}
