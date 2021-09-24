package com.iftm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.models.dtos.UserDTO;
import com.iftm.models.entities.User;
import com.iftm.repositories.UserRepository;
import com.iftm.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll() {
		List<User> users = repository.findAll();
		return users.stream().map(entity -> new UserDTO(entity)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		Optional<User> result = repository.findById(id);
		User entity = result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
		UserDTO user = new UserDTO(entity);
		
		return user;
	}

}
