package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		User currentUser = authService.getCurrentUser();
		authService.validateMember(currentUser);	
		Review entity = new Review();
		entity.setText(dto.getText());
		entity.setMovie(new Movie(dto.getMovieId(),null,null,null,null,null,null));
		entity.setUser(currentUser);
		entity = repository.save(entity);
		return new ReviewDTO(entity);
	}

	public List<ReviewDTO> findReviewsByMovie(Long movieId) {
		Movie movie = movieRepository.getOne(movieId);
		List<Review> list = repository.findReviewsByMovie(movie);
		return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
	
	
	
	

}
