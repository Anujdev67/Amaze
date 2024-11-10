package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.repository.TestAndScanRepository;

@Service
public class TestAndScansService {
	@Autowired
	private TestAndScanRepository testAndScanRepository;

	public TestAndScans insert(TestAndScans testAndScans) {
		return testAndScanRepository.save(testAndScans);
	}

}
