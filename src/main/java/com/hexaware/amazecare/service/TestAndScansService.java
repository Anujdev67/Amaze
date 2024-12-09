package com.hexaware.amazecare.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.enums.TestScanStatus;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.repository.TestAndScanRepository;

@Service
public class TestAndScansService {
	@Autowired
	private TestAndScanRepository testAndScanRepository;

	public TestAndScans insert(TestAndScans testAndScans) {
		return testAndScanRepository.save(testAndScans);
	}

	public TestAndScans validate(int tid) throws ResourceNotFoundException {
		Optional<TestAndScans>optional=testAndScanRepository.findById(tid);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Test Id not found");
		}
		return optional.get();
	}
	  public TestAndScans updateTestAndScans(int id, TestAndScans testAndScansDetails) throws ResourceNotFoundException {
	        TestAndScans testAndScans = validate(id);
	        if (testAndScansDetails.getType() != null) {
	            testAndScans.setType(testAndScansDetails.getType());
	        }
	        if (testAndScansDetails.getStatus() != null) {
	            testAndScans.setStatus(testAndScansDetails.getStatus());
	        }
	        if (testAndScansDetails.getTestOrScanPrescibedon() != null) {
	            testAndScans.setTestOrScanPrescibedon(testAndScansDetails.getTestOrScanPrescibedon());
	        }
	        if (testAndScansDetails.getDescription() != null) {
	            testAndScans.setDescription(testAndScansDetails.getDescription());
	        }
	        if (testAndScansDetails.getDoctor() != null) {
	            testAndScans.setDoctor(testAndScansDetails.getDoctor());
	        }
	        if (testAndScansDetails.getPatient() != null) {
	            testAndScans.setPatient(testAndScansDetails.getPatient());
	        }
	        return testAndScanRepository.save(testAndScans);
	    }

	    public void deleteTestAndScans(int id) throws ResourceNotFoundException {
	        TestAndScans testAndScans = validate(id);
	        testAndScanRepository.delete(testAndScans);
	    }

	    public List<TestAndScans> findAll() {
	        return testAndScanRepository.findAll();
	    }
	    public List<TestAndScans>findByStatus(TestScanStatus status){
	    	return testAndScanRepository.findByStatus(status);
	    	
	    }

	    public TestAndScans updateMultipleField(int id,String description,TestScanStatus status)throws ResourceNotFoundException{
	    	TestAndScans testAndScans = validate(id);
	    	if(description != null) {
	    		testAndScans.setDescription(description);
	    		
	    	}
	    	if(status != null) {
	    		testAndScans.setStatus(status);
	    	}
	    	return testAndScanRepository.save(testAndScans);
	    }


	    public List<TestAndScans> findByDoctorName(String doctorName) {
	        return testAndScanRepository.findByDoctorName(doctorName);
	    }

		public List<TestAndScans> findByPatientName(String patientName) {
			return testAndScanRepository.findByPatientName(patientName);
		}

		public Page<TestAndScans> findAll(Pageable pageable) {

			return testAndScanRepository.findAll(pageable);
		}

	    
	    
}






//
//public List<TestAndScans>findByDate(LocalDate date){
//return testAndScanRepository.findByTestOrScanPrescibedon(date);
//}