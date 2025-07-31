package com.icinema.movies.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.icinema.movies.dto.MoviesDto;
import com.icinema.movies.entities.Movies;
import com.icinema.movies.exception.IcinemaException;
import com.icinema.movies.repository.MoviesRepository;
import com.icinema.movies.utility.Genre;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class MoviesServiceImpl implements MoviesService{
	
	private MoviesRepository moviesRepository;
	
	public MoviesServiceImpl(@Autowired MoviesRepository movies) {
		this.moviesRepository = movies;
	}
	ModelMapper modelMapper = new ModelMapper();
@Override
	public List<MoviesDto>fetchAllMovies() throws IcinemaException{
		List<Movies>movies = moviesRepository.findAll();
		return modelMapper.map(movies, new TypeToken<List<MoviesDto>>() {}.getType());
	}

@Override
public MoviesDto fetchByMovieId(Integer movieId) throws IcinemaException{
	Optional<Movies> movieOp = moviesRepository.findById(movieId);
	Movies movie = movieOp.orElseThrow(()->new IcinemaException("Service.MOVIE_NOT_FOUND"));
	return modelMapper.map(movie, MoviesDto.class);
}
@Override
public List<MoviesDto>getMovies(String movieName, Boolean isScreening, Genre genre, String language, Double rating, Pageable pageInfo) throws IcinemaException{
	List<Boolean> screeningList = new ArrayList<>();
	if(isScreening == null) {
		screeningList.add(true);
		screeningList.add(false);
	} else {
		screeningList.add(isScreening);
	}
	String name = "";
	if(movieName==null||movieName.isEmpty()) {
		name="%";
	}else {
		name="%"+movieName+"%";
	}
	List<Genre>genreList = new ArrayList<>();
	if(genre==null) {
		Collections.addAll(genreList, Genre.values());
	}else {
		genreList.add(genre);
	}
	if(language==null || language.isEmpty()) {
		language="%";
	}
	if(rating==null) {
		rating=0.0;
	}
	return moviesRepository.getMovies(name, screeningList, genreList, language, rating, pageInfo).stream().map(movie->modelMapper.map(movie, MoviesDto.class)).collect(Collectors.toList());	
}
}
