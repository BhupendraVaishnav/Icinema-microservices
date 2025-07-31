package com.icinema.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.icinema.booking.dto.BookingDTO;
import com.icinema.booking.entities.Booking;
import com.icinema.booking.exception.ICNotFound;
import com.icinema.booking.repository.BookingRepository;

public class BookingServiceImpl implements BookingService{
	@Autowired
	BookingRepository repo;
	
	public List<BookingDTO> getByUserName(String username){
		List<Booking> bookings = repo.findByUsername(username);
		if(bookings ==null) return new ArrayList<>();
		return bookings.stream().map(x->new BookingDTO(x)).collect(Collectors.toList());
		
	}
@Override
	public BookingDTO getById(Integer bookingId) {
		Booking b = repo.findById(bookingId).orElseThrow(()->new ICNotFound("Booking not found"));
		return new BookingDTO(b);
	}

public BookingDTO createBooking(BookingDTO bDto) {
	Booking b = bDto.getEntity();
	Booking newB = repo.save(b);
	return new BookingDTO(newB);
}
}
