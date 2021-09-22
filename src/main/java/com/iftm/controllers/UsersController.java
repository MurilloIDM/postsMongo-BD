package com.iftm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
