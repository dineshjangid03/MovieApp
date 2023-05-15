package com.onito.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.onito.model.Movie;




public interface MovieRepo extends JpaRepository<Movie, String>{
	
	public List<Movie> findTop10ByOrderByRuntimeMinutesDesc();
	
	
	public List<Movie> findByRatingAverageRatingGreaterThan(Double averageRating);
	
	
	@Query("SELECT m.genres, m.primaryTitle, SUM(r.numVotes) FROM Movie m JOIN m.rating r ON m.tconst = r.tconst GROUP BY m.genres, m.primaryTitle")
    public List<Object[]> getGenreMoviesWithSubtotals();
	
    
	@Transactional
	@Modifying
    @Query("UPDATE Movie m SET m.runtimeMinutes = CASE WHEN m.genres = 'Documentary' THEN m.runtimeMinutes + 15 WHEN m.genres = 'Animation' THEN m.runtimeMinutes + 30 ELSE m.runtimeMinutes + 45 END")
    public void updateRuntimeMinutes();
	
}
