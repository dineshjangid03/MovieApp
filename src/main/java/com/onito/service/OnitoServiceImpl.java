package com.onito.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onito.dto.GenreMovieSubtotalDTO;
import com.onito.dto.LongestMovieDTO;
import com.onito.dto.TopRatedMovieDTO;
import com.onito.model.Movie;
import com.onito.repo.MovieRepo;

@Service
public class OnitoServiceImpl implements OnitoService{
	
	
	@Autowired
	private MovieRepo movieRepo;

	/**
	 * This method retrieves the top 10 movies with the longest duration. 
	 * It uses the findTop10ByOrderByRuntimeMinutesDesc() method of the movieRepo to get the movies.
	 * Then, it maps the retrieved movies to LongestMovieDTO objects and collects them into a list.
	 */
	@Override
	public List<LongestMovieDTO> getLongestDurationMovies() {
		List<Movie> movies = movieRepo.findTop10ByOrderByRuntimeMinutesDesc();
        return movies.stream()
                .map(movie -> new LongestMovieDTO(movie.getTconst(), movie.getPrimaryTitle(), movie.getRuntimeMinutes(), movie.getGenres()))
                .collect(Collectors.toList());
	}

	/**
	 * This method is responsible for creating a new movie. 
	 * It saves the provided movie object using the save() method of the movieRepo.
	 * It returns a success message indicating that the movie has been created successfully.
	 */
	@Override
	public String createNewMovie(Movie movie) {
		movieRepo.save(movie);
		return "success";
	}

	/**
	 * This method retrieves a list of movies with an average rating greater than 6.0.
	 * It uses the findByRatingAverageRatingGreaterThan() method of the movieRepo to get the movies.
	 * Then, it sorts the movies based on the average rating in descending order. 
	 * If two movies have the same average rating, it compares them based on their tconst values.
	 * It maps the sorted movies to TopRatedMovieDTO objects and collects them into a list.
	 * Finally, it returns the list of TopRatedMovieDTO objects.
	 */
	@Override
	public List<TopRatedMovieDTO> getTopRatedMovies() {
		List<Movie> movies=movieRepo.findByRatingAverageRatingGreaterThan(6.0);
	    
	    movies.sort(new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				if(o1.getRating().getAverageRating()>o2.getRating().getAverageRating())
					return -1;
				
				if(o1.getRating().getAverageRating()<o2.getRating().getAverageRating())
					return 1;
				
				return o1.getTconst().compareTo(o2.getTconst());
			}
		});
	    
	    return movies.stream()
	            .map(movie -> new TopRatedMovieDTO(movie.getTconst(), movie.getPrimaryTitle(), movie.getGenres(), movie.getRating().getAverageRating()))
	            .collect(Collectors.toList());
	    
	}

	/**
	 * This method retrieves a list of movies genre-wise with subtotals of their numVotes.
	 * It uses the getGenreMoviesWithSubtotals() method of the movieRepo to fetch the data as an array of objects.
	 * It iterates over the retrieved data, extracts the genre, primaryTitle, and numVotes values, and creates GenreMovieSubtotalDTO objects.
	 * It maintains a map to group the movies by genre.
	 * It then calculates the subtotal of numVotes for each genre and adds a special entry for the total votes.
	 * Finally, it returns the list of GenreMovieSubtotalDTO objects.
	 */
	@Override
	public List<GenreMovieSubtotalDTO> getGenreMoviesWithSubtotals() {

		List<Object[]> data=movieRepo.getGenreMoviesWithSubtotals();
        
        List<GenreMovieSubtotalDTO> result = new ArrayList<>();
        
        Map<String,List<GenreMovieSubtotalDTO>> map=new HashMap<>();
        
        for (Object[] obj : data) {
            String genre = (String) obj[0];
            String primaryTitle = (String) obj[1];
            Long numVotes = (Long) obj[2];
            
            List<GenreMovieSubtotalDTO> temp=map.getOrDefault(genre, new ArrayList<>());
            temp.add(new GenreMovieSubtotalDTO(genre, primaryTitle, numVotes));
            
            map.put(genre, temp);
        }
        
        for(Map.Entry<String, List<GenreMovieSubtotalDTO>> m:map.entrySet()) {
        	long total=0;
        	List<GenreMovieSubtotalDTO>temp=m.getValue();
        	result.addAll(temp);
        	
        	for(GenreMovieSubtotalDTO g:temp) {
        		total+=g.getNumVotes();
        	}
        	result.add(new GenreMovieSubtotalDTO("","Total",total));
        }
        
        return result;
            
	}

	/**
	 * This method updates the runtimeMinutes of all movies based on their genre using an SQL query.
	 * It invokes the updateRuntimeMinutes() method of the movieRepo to execute the update query.
	 * It returns a success message indicating that the runtimeMinutes have been updated successfully.
	 */
	@Override
	public String updateRuntimeMinutes() {
		movieRepo.updateRuntimeMinutes();
        return "Runtime minutes updated successfully.";
	}

}
