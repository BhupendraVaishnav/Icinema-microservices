package com.icinema.seating.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icinema.seating.entities.Seating;

public interface SeatingRepository extends JpaRepository<Seating, Integer> {
	List<Seating> findByShowIdAndShowDate(Integer showId, LocalDate showDate);
	Optional<Seating> findByShowIdAndRow(Integer showId, String seat);

}
