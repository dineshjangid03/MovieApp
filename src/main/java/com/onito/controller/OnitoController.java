package com.onito.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onito.dto.GenreMovieSubtotalDTO;
import com.onito.dto.LongestMovieDTO;
import com.onito.dto.TopRatedMovieDTO;
import com.onito.model.Movie;
import com.onito.service.CSVDataLoader;
import com.onito.service.OnitoService;
import com.opencsv.exceptions.CsvException;

@RestController
@RequestMapping("/api/v1")
public class OnitoController {
	
	@Autowired
	private CSVDataLoader csvDataLoader;
	
	@Autowired
	private OnitoService onitoService;
	
	/**
	 * GET /api/v1/movie/data
	 * This method is responsible for adding movie data to the database from movie.csv file
	 * it returns a ResponseEntity with a success message indicating that the movies data has been populated from the "movie.csv" file.
	 */
	@GetMapping("/movie/data")
	public ResponseEntity<String> addMovieData() throws IOException, CsvException{
		csvDataLoader.loadMovieData();
		return new ResponseEntity<String>("movies data populated from movie.csv file",HttpStatus.OK);
	}
	
	/**
	 * GET /api/v1/rating/data
	 * This method is responsible for adding rating data to the database from rating.csv file
	 * it returns a ResponseEntity with a success message indicating that the rating data has been populated from the "rating.csv" file.
	 */
	@GetMapping("/rating/data")
	public ResponseEntity<String> addRatingData() throws IOException, CsvException{
		csvDataLoader.loadRatingData();
		return new ResponseEntity<String>("ratings data populated from rating.csv file",HttpStatus.OK);
	}
	
	/**
     * GET /api/v1/longest-duration-movies
     * Retrieves a list of movies with the longest duration.
     * Returns a ResponseEntity containing the list of LongestMovieDTO objects.
     */
	@GetMapping("/longest-duration-movies")
    public ResponseEntity<List<LongestMovieDTO>> getLongestDurationMovies() {
        List<LongestMovieDTO>result=onitoService.getLongestDurationMovies();
        return new ResponseEntity<List<LongestMovieDTO>>(result,HttpStatus.OK);
    }
	
	/**
     * POST /api/v1/new-movie
     * Creates a new movie.
     * Expects a JSON payload with the movie details in the request body.
     * Returns a ResponseEntity with a success message if the movie is created successfully,
     * or an appropriate error message if the creation fails.
     */
	@PostMapping("/new-movie")
    public ResponseEntity<String> createNewMovie(@RequestBody Movie movie) {
		String result=onitoService.createNewMovie(movie);
        return new ResponseEntity<String>(result,HttpStatus.CREATED);
    }
	
	/**
     * GET /api/v1/top-rated-movies
     * Retrieves a list of top-rated movies.
     * Returns a ResponseEntity containing the list of TopRatedMovieDTO objects.
     */
	@GetMapping("/top-rated-movies")
	public ResponseEntity<List<TopRatedMovieDTO>> getTopRatedMovies() {
	    List<TopRatedMovieDTO> result=onitoService.getTopRatedMovies();
	    return new ResponseEntity<List<TopRatedMovieDTO>>(result,HttpStatus.OK);
	}
	
	/**
     * GET /api/v1/genre-movies-with-subtotals
     * Retrieves a list of movies genre-wise with subtotals of their numVotes.
     * The calculation of subtotals is done in the SQL query, not the API code.
     * Returns a ResponseEntity containing the list of GenreMovieSubtotalDTO objects.
     */
	@GetMapping("/genre-movies-with-subtotals")
    public ResponseEntity<List<GenreMovieSubtotalDTO>> getGenreMoviesWithSubtotals() {
		List<GenreMovieSubtotalDTO> result=onitoService.getGenreMoviesWithSubtotals();
		return new ResponseEntity<List<GenreMovieSubtotalDTO>>(result,HttpStatus.OK);
    }
	
	/**
     * POST /api/v1/update-runtime-minutes
     * Increments the runtimeMinutes of all movies based on their genre using an SQL query.
     * Returns a ResponseEntity with a success message if the update is successful,
     * or an appropriate error message if the update fails.
     */
	@PostMapping("/update-runtime-minutes")
    public ResponseEntity<String> updateRuntimeMinutes() {
		String result=onitoService.updateRuntimeMinutes();
        return new ResponseEntity<String>(result,HttpStatus.OK);
    }


}
