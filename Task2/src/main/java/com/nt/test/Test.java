package com.nt.test;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.Movie;

public class Test {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		ObjectMapper objectMapper = new ObjectMapper();
		String cast=null,y=null;
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
		
		System.out.println("Enter Cast:");
		cast=sc.nextLine();//user to enter cast name
		
		TreeMap<String,String> year=new TreeMap<>();
		  for(int i=0;i<movie.size();i++) {
			  for(int j=0;j<movie.get(i).getCast().size();j++) {
				  if(cast.equalsIgnoreCase(movie.get(i).getCast().get(j))){
					  year.put(movie.get(i).getYear(),movie.get(i).getTitle());
				  }
			  }
		  }
		  
		  NavigableSet<String> res=year.descendingKeySet();
		  //printing the year in descending order
		  System.out.println(res);
		  
		  System.out.println("Enter year:");//user to enter year
		  y=sc.nextLine();
		  
		  for(Map.Entry<String,String> entry:year.entrySet()) {
			  if(y.equalsIgnoreCase(entry.getKey())) {
				  System.out.println(entry.getValue());
				  break;
			  }
		  }
		  
		 
		
	}

}
