package com.rodrigo.mongodbworkshop.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.mongodbworkshop.domain.Post;
import com.rodrigo.mongodbworkshop.repository.PostRepository;
import com.rodrigo.mongodbworkshop.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post não Encontrado!"));
	}

	public List<Post> findByTitle(String text) {
//		return repository.findByTitleContainingIgnoreCase(text);
		return repository.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + (24 + 60 + 60 + 100)); // Acrescentando 1 dia a mais à 'maxDate'
		return repository.fullSearch(text, minDate, maxDate);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

}
