package com.iftm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.models.dtos.PostDTO;
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
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);

		entity = repository.insert(entity);
		return new UserDTO(entity);
	}
	
	public UserDTO update(UserDTO dto, String id) {
		UserDTO user = this.findById(id);
		User entity = new User(user.getId(), user.getEmail(), user.getName());
		
		copyDtoToEntity(dto, entity);
		entity = this.repository.save(entity);
		
		return new UserDTO(entity);
	}
	
	public void delete(String id) {
		this.findById(id);
		this.repository.deleteById(id);
	}
	
	public List<PostDTO> getUserPosts(String id) {
		User entity = getEntityById(id);
		return entity.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	public User getEntityById(String id) {
		Optional<User> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}

}
