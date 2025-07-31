package com.icinema.seating.entities;

import java.time.LocalDate;
import java.util.BitSet;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seating {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Integer id;
@Column(name="theatre_movie_show_id", nullable= false)
private Integer showId;
@Column(name = "show_date", nullable=false)
private LocalDate showDate;
@Column(name ="seat_row", nullable=false, length=1)
private String row;
@Column(name = "booking_data", nullable=false)
@Convert(converter=BitSetConverter.class)

private BitSet bookingData;
}
