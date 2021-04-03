package com.nt.service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.Movie;
import com.nt.repository.MovieRepository;

@Service
public class MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	public void saveMovie() {
		ObjectMapper objectMapper=new ObjectMapper();
		List<Movie> movie=null;
		try {
			URL url = new URL("https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json");
		
			//parsed and generate list of Movie Object 
			movie = objectMapper.readValue(url, new TypeReference<List<Movie>>(){});
		}catch(JsonParseException jsp) {
			jsp.printStackTrace();
		}catch(JsonMappingException jme) {
			jme.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		for(Movie m:movie) {
			if(m!=null)
				movieRepository.save(m);
		}
		//movieRepository.saveAll(movie);
	}
	
	public List<Movie> getMovieByGenre(String genre){
		
		return movieRepository.findByGenres(genre);
	}
}
