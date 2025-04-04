package com.example.GetItDone.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GetItDone.domain.User;
import com.example.GetItDone.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public Optional<User>findUserByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public User getUserByUsername(String username) {
	    return userRepository.findByUsername(username)
	            .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
	}

}
