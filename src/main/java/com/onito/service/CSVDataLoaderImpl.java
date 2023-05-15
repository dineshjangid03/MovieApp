package com.onito.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onito.model.Movie;
import com.onito.model.Rating;
import com.onito.repo.MovieRepo;
import com.onito.repo.RatingRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class CSVDataLoaderImpl implements CSVDataLoader{
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private RatingRepo ratingRepo;

	
	/**
	 * This method is responsible for loading movie data from a CSV file and populating it in the application.
	 * It reads the CSV file located at the specified path ("src/main/resources/static/movies.csv") using a CSVReader.
	 * It reads all the rows from the CSV file, excluding the header row.
	 * For each row, it creates a new Movie object, sets the corresponding values from the CSV row, and saves the Movie object using the save() method of the movieRepo.
	 * This method assumes that the movieRepo object refers to an instance of the MovieRepo class, which handles database operations related to movies.
	 */
	@Override
    public void loadMovieData() throws IOException, CsvException {
    	String path="src/main/resources/static/movies.csv";
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();
            
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Movie movie = new Movie();
                movie.setTconst(row[0]);
                movie.setTitleType(row[1]);
                movie.setPrimaryTitle(row[2]);
                movie.setRuntimeMinutes(Integer.parseInt(row[3]));
                movie.setGenres(row[4]);
                
                movieRepo.save(movie);
            }
        }
    }

	
	/**
	 * This method is responsible for loading rating data from a CSV file and populating it in the application.
	 * It reads the CSV file located at the specified path ("src/main/resources/static/ratings.csv") using a CSVReader.
	 * It reads all the rows from the CSV file, excluding the header row.
	 * For each row, it creates a new Rating object, sets the corresponding values from the CSV row, and saves the Rating object using the save() method of the ratingRepo.
	 * This method assumes that the ratingRepo object refers to an instance of the RatingRepo class, which handles database operations related to ratings.
	 */
	@Override
    public void loadRatingData() throws IOException, CsvException {
    	
    	String path="src/main/resources/static/ratings.csv";
    	
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Rating rating = new Rating();
                rating.setTconst(row[0]);
                rating.setAverageRating(Double.parseDouble(row[1]));
                rating.setNumVotes(Integer.parseInt(row[2]));

                ratingRepo.save(rating);
            }
        }
    }
}
