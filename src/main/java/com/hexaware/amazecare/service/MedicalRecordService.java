package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.MedicalRecord;
import com.hexaware.amazecare.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public MedicalRecord insert(MedicalRecord medicalRecord) {
		return medicalRecordRepository.save(medicalRecord);
	}

}
