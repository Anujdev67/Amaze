package com.hexaware.amazecare.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.service.TestAndScansService;

@RestController
public class TestAndScansController {

	@Autowired
	private TestAndScansService testAndScansService;
	
	@PostMapping("/api/test-and-scans")
	public ResponseEntity<TestAndScans> createTestAndScans(@RequestBody TestAndScans testAndScans){
		TestAndScans createdTestAndScans = testAndScansService.insert(testAndScans);
		return ResponseEntity.ok(createdTestAndScans);
	}
	@GetMapping("/api/test-and-scans/{id}")
	public ResponseEntity<TestAndScans> getTestAndScansById(@PathVariable int id)throws ResourceNotFoundException{
		TestAndScans testAndScans = testAndScansService.validate(id);
		return ResponseEntity.ok(testAndScans);
	}
	@PutMapping("/api/test-and-scans/{id}")
    public ResponseEntity<TestAndScans> updateTestAndScans(@PathVariable int id, @RequestBody TestAndScans testAndScansDetails) throws ResourceNotFoundException {
        TestAndScans updatedTestAndScans = testAndScansService.updateTestAndScans(id, testAndScansDetails);
        return ResponseEntity.ok(updatedTestAndScans);
    }

}
