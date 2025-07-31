package com.icinema.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
@Configuration
public class MoviesConfig {
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
