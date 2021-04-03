package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.model.Movie;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String>{
	
	List<Movie> findByGenres(String genres);
}
