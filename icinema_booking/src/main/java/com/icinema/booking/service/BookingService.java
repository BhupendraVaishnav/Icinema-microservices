package com.icinema.booking.service;

import java.util.List;

import com.icinema.booking.dto.BookingDTO;
import com.icinema.booking.exception.ICNotFound;

public interface BookingService {
	public List<BookingDTO> getByUserName(String username);
	public BookingDTO getById(Integer bookingId) throws ICNotFound;
	public BookingDTO createBooking(BookingDTO bDto);
	
}
