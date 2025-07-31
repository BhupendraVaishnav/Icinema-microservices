package com.icinema.movies.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.icinema.movies.dto.MoviesDto;
import com.icinema.movies.exception.IcinemaException;
import com.icinema.movies.utility.Genre;

public interface MoviesService {
public List<MoviesDto> fetchAllMovies() throws IcinemaException;
public MoviesDto fetchByMovieId(Integer movieId) throws IcinemaException;
public List<MoviesDto> getMovies(String movieName, Boolean isScreening, Genre genre, String language, Double rating, Pageable pageInfo) throws IcinemaException;



}
