package com.onito.service;

import java.util.List;

import com.onito.dto.GenreMovieSubtotalDTO;
import com.onito.dto.LongestMovieDTO;
import com.onito.dto.TopRatedMovieDTO;
import com.onito.model.Movie;

public interface OnitoService {

    public List<LongestMovieDTO> getLongestDurationMovies() ;
    
    public String createNewMovie(Movie movie) ;
    
	public List<TopRatedMovieDTO> getTopRatedMovies();
	
    public List<GenreMovieSubtotalDTO> getGenreMoviesWithSubtotals() ;
    
    public String updateRuntimeMinutes();

}
