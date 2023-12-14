package com.learn.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.hotel.entities.Hotel;
import com.learn.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	//	create
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
	}
	
	//	get single
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getSingle(String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getString(hotelId));
	}
	
	//get all	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAll(){
		return ResponseEntity.ok(hotelService.getAll());
	}
	
}
