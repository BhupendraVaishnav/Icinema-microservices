package com.icinema.movies;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.icinema.movies.dto.MoviesDto;
import com.icinema.movies.entities.Movies;
import com.icinema.movies.exception.IcinemaException;
import com.icinema.movies.repository.MoviesRepository;
import com.icinema.movies.service.MoviesService;
import com.icinema.movies.service.MoviesServiceImpl;
import com.icinema.movies.utility.Genre;

@SpringBootTest
class IcinemaMoviesApplicationTests {
@Mock
	MoviesRepository repo;
@InjectMocks
MoviesService serv = new MoviesServiceImpl(repo);

ModelMapper mapper = new ModelMapper();

@Test
void getMoviesValid() throws IcinemaException{
	String name = "name";
	List<Boolean> isScreening = Arrays.asList(true);
	List<Genre> genre = Arrays.asList(Genre.ACTION);
	String language = "Hindi";
	Double rating = 8.0;
	Pageable pageInfo = PageRequest.of(0, 1);
	
	Movies m1 = new Movies(); m1.setId(1001);
	Movies m2 = new Movies(); m2.setId(1002);
	Movies m3 = new Movies(); m3.setId(1003);
	Movies m4 = new Movies(); m4.setId(1004);
	Movies m5 = new Movies(); m5.setId(1005);
	
	Mockito.when(repo.getMovies("%", isScreening, genre, language, rating, pageInfo)).thenReturn(Arrays.asList(m1));
	Mockito.when(repo.getMovies("%"+name+"%", Arrays.asList(true,false),genre,language,rating,pageInfo)).thenReturn(Arrays.asList(m2));
	Mockito.when(repo.getMovies("%"+name+"%", isScreening, Arrays.asList(Genre.values()), language, rating, pageInfo)).thenReturn(Arrays.asList(m3));
	Mockito.when(repo.getMovies("%"+name+"%", isScreening, genre, "%", rating, pageInfo)).thenReturn(Arrays.asList(m4));
	Mockito.when(repo.getMovies("%"+name+"%", isScreening, genre, language, 0.0, pageInfo)).thenReturn(Arrays.asList(m5));
	
	Assertions.assertEquals(Arrays.asList(mapper.map(m1, MoviesDto.class)), serv.getMovies(null, true, Genre.ACTION, language, rating, pageInfo), "m1 does not match");
	Assertions.assertEquals(Arrays.asList(mapper.map(m2, MoviesDto.class)), serv.getMovies(name, null, Genre.ACTION, language, rating, pageInfo), "m2 does not match");
	Assertions.assertEquals(Arrays.asList(mapper.map(m3, MoviesDto.class)), serv.getMovies(name, true, null, language, rating, pageInfo), "m3 does not match");
	
	
	Assertions.assertEquals(Arrays.asList(mapper.map(m4, MoviesDto.class)), serv.getMovies(name, true, Genre.ACTION, null, rating, pageInfo), "m4 does not match");
	Assertions.assertEquals(Arrays.asList(mapper.map(m5, MoviesDto.class)), serv.getMovies(name, true, Genre.ACTION, language, null, pageInfo), "m5 does not match");
	
	
}
@Test
void testFetchAllMovies() throws IcinemaException{
	List<Movies> moviesList;
	List<MoviesDto> moviesDtoList;
	Movies movie1 =new Movies();
	movie1.setId(1);
	movie1.setMovieName("Movie 1");
	movie1.setMovieGenre(Genre.ACTION);
	Movies movie2 = new Movies();
	movie2.setId(2);
	movie2.setMovieName("Movie 2");
	movie2.setMovieGenre(Genre.ADULT);
	moviesList = Arrays.asList(movie1, movie2);
	MoviesDto dto1 =new MoviesDto();
	dto1.setId(1);
	dto1.setMovieName("Movie 1");
	dto1.setMovieGenre(Genre.ACTION);
	MoviesDto dto2 = new MoviesDto();
	dto2.setId(2);
	dto2.setMovieName("Movie 2");
	dto2.setMovieGenre(Genre.ADULT);
	moviesDtoList = Arrays.asList(dto1,dto2);
	Mockito.when(repo.findAll()).thenReturn(moviesList);
	List<MoviesDto> result = serv.fetchAllMovies();
	Assertions.assertNotNull(result);
	Assertions.assertEquals(2, result.size());
	Assertions.assertEquals("Movie 1", result.get(0).getMovieName());
	Assertions.assertEquals("Movie 2", result.get(1).getMovieName());
	
}
@Test
void getMovieValid() throws IcinemaException {
	Integer movieId = 10000;
	Movies mockMovie = new Movies();
	mockMovie.setCensorRating("A");
	mockMovie.setCoverImage("cover_img");
	mockMovie.setMovieDescription("description");
	mockMovie.setMovieGenre(Genre.ACTION);
	mockMovie.setId(movieId);
	mockMovie.setIsScreening(true);
	mockMovie.setMovieLanguage("Hindi");
	mockMovie.setMovieName("movie name");
	mockMovie.setRating(8.0);
	mockMovie.setReleaseDate(LocalDate.now());
	mockMovie.setViewCount(40034);
	Mockito.when(repo.findById(movieId)).thenReturn(Optional.of(mockMovie));
	MoviesDto mdto = serv.fetchByMovieId(movieId);
	Assertions.assertEquals(mockMovie, mapper.map(mdto, Movies.class));
	
}

@Test
void getMovieInvalid() {
	Integer movieId =10000;
	Mockito.when(repo.findById(movieId)).thenReturn(Optional.empty());
	Assertions.assertThrows(IcinemaException.class, ()->serv.fetchByMovieId(movieId));
}
}
