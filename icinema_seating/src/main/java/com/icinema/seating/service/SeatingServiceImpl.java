package com.icinema.seating.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icinema.seating.dto.BookSeatReqDto;
import com.icinema.seating.dto.SeatingDTO;
import com.icinema.seating.entities.RowEnum;
import com.icinema.seating.entities.Seating;
import com.icinema.seating.repository.SeatingRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SeatingServiceImpl implements SeatingService{
	@Autowired
	SeatingRepository repo;
	@Override
	public SeatingDTO getForShow(Integer showId, LocalDate showDate) {
		List<Seating> reporesult = repo.findByShowIdAndShowDate(showId, showDate);
		log.info("After Rpo: {}", reporesult);
		SeatingDTO res = SeatingDTO.builder().showDate(showDate).showId(showId).bookingData(new ArrayList<>()).build();
		for(RowEnum row: RowEnum.values()) {
			var filteredRow = reporesult.stream().filter((seatRow)->seatRow.getRow().equals(row.name())).collect(Collectors.toList());
			log.info("After filtering: {}", filteredRow);
			BitSet bitSet;
			if(!Objects.isNull(filteredRow)&& filteredRow.size()>0) {
				bitSet = filteredRow.get(0).getBookingData();
				log.info("After set bitset: {}", bitSet.toString());
			}else {
				bitSet = new BitSet(14);
			}
			res.getBookingData().add(bitSet.toString());
			
		}
		return res;
		
		
	}
	@Override
	public List<Integer>bookSeat(List<BookSeatReqDto> bookSeatListReqDto){
		List<Integer> res = new ArrayList<>();
		bookSeatListReqDto.forEach((bookSeatReqDto)->{
			String seatRow = bookSeatReqDto.getRowId();
			Optional<Seating>seatingOpt = repo.findByShowIdAndRow(bookSeatReqDto.getShowId(),seatRow);
			Seating seat;
			if(seatingOpt.isPresent()) {
				seat = seatingOpt.get();
			} else {
				BitSet booking = new BitSet(16);
				seat = Seating.builder().row(seatRow).showId(bookSeatReqDto.getShowId())
						.showDate(bookSeatReqDto.getShowDate()).bookingData(booking).build();
			}
			BitSet bookingData = seat.getBookingData();
			bookSeatReqDto.getSeatNos().forEach((seatRowNo)->{
				if(seatRowNo>14) {
					return;
				}
				bookingData.set(seatRowNo-1, true);
			});
			res.add(repo.save(seat).getId());
		});
		return res;
	}
	
	

}
