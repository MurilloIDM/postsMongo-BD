package com.iftm.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iftm.models.dtos.PostDTO;
import com.iftm.models.dtos.UserDTO;
import com.iftm.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService service;
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> findall() {
		List<UserDTO> users = this.service.findAll();
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(
		@PathVariable String id
	) {
		UserDTO user = this.service.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping()
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		dto = this.service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(
		@PathVariable String id,
		@RequestBody UserDTO dto
	) {
		dto = service.update(dto, id);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.service.delete(id);
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<PostDTO>> getUsersPosts(@PathVariable String id) {
		List<PostDTO> list = service.getUserPosts(id);
		return ResponseEntity.ok().body(list);
	}
}
