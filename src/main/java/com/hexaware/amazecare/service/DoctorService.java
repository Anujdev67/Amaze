package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

}
