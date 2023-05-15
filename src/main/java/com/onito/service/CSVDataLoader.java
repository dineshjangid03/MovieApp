package com.onito.service;

import java.io.IOException;

import com.opencsv.exceptions.CsvException;

public interface CSVDataLoader {
	
	public void loadMovieData() throws IOException, CsvException ;
	
	public void loadRatingData() throws IOException, CsvException ;

}
