package com.icinema.booking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.icinema.booking.dto.BookingDTO;
import com.icinema.booking.service.BookingService;

public class BookingApi {
@Autowired
BookingService serv;
@PostMapping("")
public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bDto){
	return new ResponseEntity<BookingDTO>(serv.createBooking(bDto),HttpStatus.OK);
}
@GetMapping("/user/{username}")
public ResponseEntity<List<BookingDTO>> getByusername(
		@PathVariable String username){
	return new ResponseEntity<List<BookingDTO>>(serv.getByUserName(username),HttpStatus.OK);
}
@GetMapping("/{bookingId}")
public ResponseEntity<BookingDTO> getById(@PathVariable Integer bookingId){
	return new ResponseEntity<BookingDTO>(serv.getById(bookingId),HttpStatus.OK);
}
}
