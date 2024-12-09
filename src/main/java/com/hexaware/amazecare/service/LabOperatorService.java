package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.repository.LabOperatorRepository;


@Service
public class LabOperatorService {
	Logger logger = LoggerFactory.getLogger(LabOperatorService.class);
    @Autowired
    private LabOperatorRepository labOperatorRepository;

    public LabOperator getOperator(LabOperator labOperator) {
        return labOperatorRepository.save(labOperator);
    }

    public LabOperator validate(int id) throws ResourceNotFoundException {
        return labOperatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LabOperator not found"));
    }

    public LabOperator updateLabOperator(int id, LabOperator labOperatorDetails) throws ResourceNotFoundException {
        LabOperator labOperator = validate(id);
        if (labOperatorDetails.getName() != null) {
            labOperator.setName(labOperatorDetails.getName());
        }
        if (labOperatorDetails.getExperience() != 0) {
            labOperator.setExperience(labOperatorDetails.getExperience());
        }
        if (labOperatorDetails.getSalary() != null) {
            labOperator.setSalary(labOperatorDetails.getSalary());
        }
        if (labOperatorDetails.getContact() != null) {
            labOperator.setContact(labOperatorDetails.getContact());
        }
        if (labOperatorDetails.getEmail() != null) {
            labOperator.setEmail(labOperatorDetails.getEmail());
        }
        return labOperatorRepository.save(labOperator);
    }

    public void deleteLabOperator(int id) throws ResourceNotFoundException {
        LabOperator labOperator = validate(id);
        labOperatorRepository.delete(labOperator);
    }

    public List<LabOperator> findAll() {
        return labOperatorRepository.findAll();
    }

    public Page<LabOperator> getAllLabOperators(Pageable pageable) {
        return labOperatorRepository.findAll(pageable);
    }
    public List<LabOperator> findByExperience(int experience) {
        return labOperatorRepository.findByExperience(experience);
    }
    public Optional<LabOperator> findByEmail(String email) {
        return labOperatorRepository.findByEmail(email);
    }
    
    // public List<LabOperator> getAllLabOperators(Pageable pageable) {
    //return labOperatorRepository.findAll(pageable).toList();
//}
//public void deleteLabOperator(int id)throws ResourceNotFoundException{
//	LabOperator labOperator = validate(id);
//	labOperatorRepository.delete(labOperator);
//}
//
//public LabOperator updateLabOperator(int id, LabOperator labOperatorDetails) throws ResourceNotFoundException {
//	LabOperator labOperator = validate(id);
//	labOperator.setName(labOperatorDetails.getName());
//	labOperator.setExperience(labOperatorDetails.getExperience());
//	labOperator.setSalary(labOperatorDetails.getSalary());
//	labOperator.setContact(labOperatorDetails.getContact());
//	labOperator.setEmail(labOperatorDetails.getEmail());
//	return labOperatorRepository.save(labOperator);
//}
}
