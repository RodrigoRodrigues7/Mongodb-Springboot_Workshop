package com.rodrigo.mongodbworkshop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.mongodbworkshop.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//Esse método é um 'Query Method'
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}
