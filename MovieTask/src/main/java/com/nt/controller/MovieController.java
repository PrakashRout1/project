package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Movie;
import com.nt.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/import")
	public String save() {
		movieService.saveMovie();
		return "show";
	}
	
	@RequestMapping("/Movie")
	public List<Movie> getByGenre(@RequestParam("genre") String genre){
		
		return movieService.getMovieByGenre(genre);
	}
}
