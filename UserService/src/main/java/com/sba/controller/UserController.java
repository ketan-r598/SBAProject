package com.sba.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sba.exception.IncorrectPasswordException;
import com.sba.exception.UserExistsException;
import com.sba.exception.UserNotFoundException;
import com.sba.model.User;
import com.sba.model.UserCredential;
import com.sba.service.TokenGeneratorService;
import com.sba.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private TokenGeneratorService tokenGenerator;
	
	@PostMapping("register")
	public User registerUser(@RequestBody User user) throws UserExistsException {
		return this.service.registerUser(user);
	}
	
	@PostMapping("login")
	public Map<String,String> authenticateUser(@RequestBody UserCredential credentails) throws UserNotFoundException, IncorrectPasswordException{
		System.out.println(credentails);
		final UserCredential user = this.service.authenticateUser(credentails.getUserId(), credentails.getUserPassword());
		return this.tokenGenerator.generateToken(user);
	}
	
	@PutMapping("update")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUserById(id);
	}
	
	@GetMapping("getUser/{id}")
	public User getUser(@PathVariable int id) {
		return service.getUserById(id);
	}
}
