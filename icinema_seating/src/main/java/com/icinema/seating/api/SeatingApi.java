package com.icinema.seating.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icinema.seating.dto.BookSeatReqDto;
import com.icinema.seating.dto.SeatingDTO;
import com.icinema.seating.service.SeatingService;

@RestController
@RequestMapping("/api/seating")
@CrossOrigin(origins= {"http://localhost:3000"})
public class SeatingApi {
	@Autowired
	SeatingService serv;
	@GetMapping("/showId/{showId}/showDate/{showDate}")
	public ResponseEntity<SeatingDTO>getMothodName(
			@PathVariable Integer showId, @PathVariable LocalDate showDate){
		return new ResponseEntity<SeatingDTO>(serv.getForShow(showId, showDate),HttpStatus.OK);
	}
	@PutMapping("/bookSeat")
	public ResponseEntity<String> bookSeat (@RequestBody List<BookSeatReqDto> bookSeatReqDto){
		String bookIdList = serv.bookSeat(bookSeatReqDto).toString();
		return new ResponseEntity<String>("Seat Booked Successfully with ID: "+bookIdList,HttpStatus.OK);
	}
	
}


