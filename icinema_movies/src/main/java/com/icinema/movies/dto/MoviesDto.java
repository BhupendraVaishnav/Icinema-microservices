package com.icinema.movies.dto;

import java.time.LocalDate;

import com.icinema.movies.utility.Genre;

import lombok.Data;
@Data
public class MoviesDto {
	private Integer id;
	private String movieName;
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
