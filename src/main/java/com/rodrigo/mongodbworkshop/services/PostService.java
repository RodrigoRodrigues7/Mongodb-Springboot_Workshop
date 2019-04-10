package com.rodrigo.mongodbworkshop.services;

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
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
}
