package com.icinema.seating.dto;

import java.time.LocalDate;
import java.util.List;

import com.icinema.seating.entities.Seating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatingDTO {
private Integer showId;
private LocalDate showDate;
private List<String> bookingData;

public SeatingDTO(Seating s) {
	populateFromEntity(s);
}
public SeatingDTO populateFromEntity(Seating s) {
	this.showId = s.getShowId();
	return this;
}
public Seating toEntity() {
	Seating en = new Seating();
	en.setShowId(this.showId);
	return en;
}
}
