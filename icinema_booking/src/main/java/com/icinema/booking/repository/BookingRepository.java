package com.icinema.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icinema.booking.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	public List<Booking> findByUsername(String username);

}
