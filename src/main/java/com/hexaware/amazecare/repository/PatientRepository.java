package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}