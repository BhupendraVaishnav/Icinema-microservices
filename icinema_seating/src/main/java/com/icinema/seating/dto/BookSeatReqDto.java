package com.icinema.seating.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSeatReqDto {
	private Integer showId;
	private LocalDate showDate;
	private String rowId;
	private List<Integer> seatNos;

}
