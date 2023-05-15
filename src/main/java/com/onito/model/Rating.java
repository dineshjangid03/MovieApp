package com.onito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Rating {
    @Id
    private String tconst;

    private Double averageRating;

    private Integer numVotes;

    @OneToOne
    @JoinColumn(name="tconst")
    private Movie movie;

	public String getTconst() {
		return tconst;
	}
	
	public void setTconst(String tconst) {
		this.tconst = tconst;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}
	
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	
	public Integer getNumVotes() {
		return numVotes;
	}
	
	public void setNumVotes(Integer numVotes) {
		this.numVotes = numVotes;
	}
    
}
