package com.rodrigo.mongodbworkshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.mongodbworkshop.domain.User;
import com.rodrigo.mongodbworkshop.dto.UserDTO;
import com.rodrigo.mongodbworkshop.repository.UserRepository;
import com.rodrigo.mongodbworkshop.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuário não Encontrado!"));
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public User fromDto(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
}
