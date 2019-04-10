package com.rodrigo.mongodbworkshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rodrigo.mongodbworkshop.domain.Post;
import com.rodrigo.mongodbworkshop.domain.User;
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
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem!", "Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, sdf.parse("15/02/2019"), "Bom Dia", "Acordei feliz hoje!", maria);
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		postRepo.saveAll(Arrays.asList(post1, post2));
	}
	
	
	
}
