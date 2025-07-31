package com.icinema.movies.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icinema.movies.dto.MoviesDto;
import com.icinema.movies.exception.IcinemaException;
import com.icinema.movies.service.MoviesService;
import com.icinema.movies.utility.Genre;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
@RequestMapping("/movies")
public class MoviesController {
	@Autowired
	MoviesService moviesService;

	@GetMapping("/filters")
	public ResponseEntity<List<MoviesDto>> getMovies(
			@RequestParam(required=false) String movieName,
			@RequestParam(required=false) Boolean isScreening,
			@RequestParam(required=false) Genre genre,
			@RequestParam(required = false) String language,
			@RequestParam(required=false) Double rating,
			@RequestParam(defaultValue ="0") Integer page,
			@RequestParam(defaultValue="10") Integer size)throws IcinemaException{
		Pageable pageable = PageRequest.of(page,size);
		List<MoviesDto> movies = moviesService.getMovies(movieName, isScreening, genre, language, rating, pageable);
		return ResponseEntity.ok(movies);
				
	}
	@GetMapping("/{id}")
	public ResponseEntity<MoviesDto> getMovieByMovieId(@PathVariable Integer id)throws IcinemaException{
		MoviesDto movie = moviesService.fetchByMovieId(id);
		return new ResponseEntity<MoviesDto>(movie,HttpStatus.OK);
	}
	@GetMapping("/all")
	@CircuitBreaker(name="moviesCircuitBreaker", fallbackMethod = "moviesFallback")
	public ResponseEntity<List<MoviesDto>> getAllMovies() throws IcinemaException{
		List<MoviesDto> movies = moviesService.fetchAllMovies();
		return new ResponseEntity<List<MoviesDto>>(movies, HttpStatus.OK);
	}
	public ResponseEntity<List<MoviesDto>>moviesFallback(Throwable throwable){
		
		List<MoviesDto> movies = new ArrayList<>();
		return new ResponseEntity<List<MoviesDto>>(movies,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
