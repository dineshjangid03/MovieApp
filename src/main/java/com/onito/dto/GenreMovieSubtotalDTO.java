package com.onito.dto;

public class GenreMovieSubtotalDTO {
    private String genre;
    
    private String primaryTitle;
    
    private Long numVotes;

    
    public GenreMovieSubtotalDTO() {
		// TODO Auto-generated constructor stub
	}
    
    public GenreMovieSubtotalDTO(String genre, String primaryTitle, Long numVotes) {
        this.genre = genre;
        this.primaryTitle = primaryTitle;
        this.numVotes = numVotes;
    }

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPrimaryTitle() {
		return primaryTitle;
	}

	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	public Long getNumVotes() {
		return numVotes;
	}

	public void setNumVotes(Long numVotes) {
		this.numVotes = numVotes;
	}

}