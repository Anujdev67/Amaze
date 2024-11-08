package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	public Patient insert(Patient patient) {
		return patientRepository.save(patient);
		
	}

}
