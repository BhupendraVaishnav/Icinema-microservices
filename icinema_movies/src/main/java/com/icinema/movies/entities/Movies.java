package com.icinema.movies.entities;

import java.time.LocalDate;

import com.icinema.movies.utility.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Movies {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String movieName;
	@Enumerated(EnumType.STRING)
	private Genre movieGenre;
	private String movieLanguage;
	private LocalDate releaseDate;
	private Double rating;
	private String censorRating;
	private String movieDescription;
	private Integer viewCount;
	private String coverImage;
	private Boolean isScreening;
}
