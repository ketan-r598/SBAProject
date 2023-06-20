package com.sba.service;

import com.sba.exception.IncorrectPasswordException;
import com.sba.exception.UserExistsException;
import com.sba.exception.UserNotFoundException;
import com.sba.model.User;
import com.sba.model.UserCredential;

public interface UserService {
	public User registerUser(User user) throws UserExistsException;
	public UserCredential authenticateUser(int userId,String password) throws UserNotFoundException,IncorrectPasswordException;
	public User getUserById(int id);
	public void deleteUserById(int id);
	public User updateUser(User user);
}