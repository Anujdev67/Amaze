package com.hexaware.amazecare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.InPatient;
import com.hexaware.amazecare.repository.InPatientRepository;

@Service
public class InPatientService {
	@Autowired
	private InPatientRepository inPatientRepository;

	public InPatient insert(InPatient inPatient) {
		return inPatientRepository.save(inPatient);
		
	}

	public List<InPatient> getall() {
		return inPatientRepository.findAll();
	}

}
