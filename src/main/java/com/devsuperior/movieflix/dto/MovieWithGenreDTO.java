package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Movie;

public class MovieWithGenreDTO extends MovieDTO {

	private String synopsis;
	private GenreDTO genre;
	
	
	public MovieWithGenreDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis, GenreDTO genre) {
		super(id, title, subTitle, year, imgUrl);
		this.synopsis = synopsis;
		this.genre = genre;
	}
	
	public MovieWithGenreDTO(Movie entity) {
		super(entity);
		this.synopsis = entity.getSynopsis();
		this.genre = new GenreDTO(entity.getGenre());
	}
	
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}
		
}
