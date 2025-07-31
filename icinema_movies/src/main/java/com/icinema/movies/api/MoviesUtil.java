package com.icinema.movies.api;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icinema.movies.utility.Genre;

@RestController
@RequestMapping("/utilities")
public class MoviesUtil {

	private static final String IMG_DIR = "src/main/resources/static/images/";

	@GetMapping("/genre")
	public ResponseEntity<List<Genre>>getAllGenres(){
		List<Genre> genreList = new ArrayList<>();
		Collections.addAll(genreList, Genre.values());
		return new ResponseEntity<List<Genre>>(genreList, HttpStatus.OK);
		
	}
@GetMapping("/{id}/thumbnail")
public ResponseEntity<Resource> getMovieThumbnail(@PathVariable Integer id){
	try {
		Path imagePath = Paths.get(IMG_DIR+id+".jpeg");
		if(!Files.exists(imagePath)) {
			return ResponseEntity.notFound().build();
			
		}
		Resource image = new UrlResource(imagePath.toUri());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		
	}catch(MalformedURLException e) {
		throw new RuntimeException("Image not found");
	}
}
}
