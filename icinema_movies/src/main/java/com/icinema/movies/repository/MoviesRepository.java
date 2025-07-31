package com.icinema.movies.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icinema.movies.entities.Movies;
import com.icinema.movies.utility.Genre;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
	@Query("select m FROM Movies m WHERE LOWER(m.movieName) LIKE?1and isScreening IN?2and m.movieGenre In?3 and m.movieLanguage LIKE?4 and m.rating >=?5")
	public List<Movies> getMovies(String movieName, List<Boolean>isScreening, List<Genre> movieGenre, String movieLanguage, Double rating, Pageable pageInfo);

}
