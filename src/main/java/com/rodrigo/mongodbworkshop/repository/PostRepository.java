package com.rodrigo.mongodbworkshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigo.mongodbworkshop.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// Esse método é um 'Query Method'
	List<Post> findByTitleContainingIgnoreCase(String text);

	// Esse método faz a mesma consulta que o método acima, mas usando uma '@Query'
	@Query("{ 'title': {$regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String text);

	@Query("{ $and: [{date: {$gte: ?1}}, {date: {$lte: ?2}}, {$or: [{ 'title': {$regex: ?0, $options: 'i'} }, { 'body': {$regex: ?0, $options: 'i'} }, { 'comments.text': {$regex: ?0, $options: 'i'} }]}] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
