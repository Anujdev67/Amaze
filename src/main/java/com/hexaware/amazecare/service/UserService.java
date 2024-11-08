package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User insert(User user) {
		return userRepository.save(user);
	}

}
