package com.icinema.booking.dto;

import com.icinema.booking.entities.Booking;

import lombok.Data;

@Data
public class BookingDTO {
private Integer id;
private String username;
private Integer showId;
public BookingDTO() {}
public BookingDTO(Booking b) {
	this.populateFromEntity(b);
}
public BookingDTO populateFromEntity(Booking b) {
	this.setId(b.getId());
	this.setUsername(b.getUsername());
	this.setShowId(b.getShowId());
	return this;
}
public Booking getEntity() {
	Booking en = new Booking();
	en.setId(id);
	en.setUsername(username);
	en.setShowId(showId);
	return en;
}
}
