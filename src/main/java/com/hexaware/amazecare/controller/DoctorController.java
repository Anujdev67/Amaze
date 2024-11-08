package com.hexaware.amazecare.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.UserService;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/doctor/register")
	public Doctor registerDoctor(@RequestBody Doctor doctor) {
		User user=new User();
		user.setUsername(doctor.getUser().getUsername());
		user.setPassword(doctor.getUser().getUsername());
		user.setRole(doctor.getUser().getRole());
		user=userService.insert(user);
		doctor.setUser(user);
		doctor.setJoiningDate(LocalDate.now());
		
		return doctorService.insert(doctor);
	}

}
