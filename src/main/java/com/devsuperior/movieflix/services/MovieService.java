package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieWithGenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Transactional(readOnly = true) // readOnly = true, evita que o banco fique em lock durante a consulta
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new MovieWithGenreDTO(entity);
	}


	@Transactional(readOnly = true) // readOnly = true, evita que o banco fique em lock durante a consulta
	public Page<MovieDTO> findbyGenrePaged(Pageable pageable, Long genreId) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = repository.findbyGenrePaged(genre, pageable);
		return (genreId == 0) ? page.map(x -> new MovieDTO(x)) : page.map(x -> new MovieWithGenreDTO(x));
	}
	

}
