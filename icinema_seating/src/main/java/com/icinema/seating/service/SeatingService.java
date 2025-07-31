package com.icinema.seating.service;

import java.time.LocalDate;
import java.util.List;

import com.icinema.seating.dto.BookSeatReqDto;
import com.icinema.seating.dto.SeatingDTO;

public interface SeatingService {
public SeatingDTO getForShow(Integer showId, LocalDate showDate);
public List<Integer> bookSeat(List<BookSeatReqDto>bookSeatReqDto);
}
