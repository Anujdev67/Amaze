
package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.enums.Role;
import com.hexaware.amazecare.exceptions.InvalidUsernameException;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	public User insert(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	// check if username is duplicate
	public User signUp(User user) throws InvalidUsernameException {
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if (optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
//encryption
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		user.setRole(Role.LAB_OPERATOR);
		return userRepository.save(user);
	}

	public void updatePasswords() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		}
	}

}
