package com.rodrigo.mongodbworkshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rodrigo.mongodbworkshop.domain.Post;
import com.rodrigo.mongodbworkshop.domain.User;
import com.rodrigo.mongodbworkshop.dto.AuthorDTO;
import com.rodrigo.mongodbworkshop.dto.CommentDTO;
import com.rodrigo.mongodbworkshop.repository.PostRepository;
import com.rodrigo.mongodbworkshop.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo ;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("15/02/2019"), "Bom Dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("15/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
	}
	
	
	
}
