package com.hexaware.amazecare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.amazecare.model.LabOperator;

public interface LabOperatorRepository extends JpaRepository<LabOperator, Integer> {
	
	@Query("SELECT l FROM LabOperator l WHERE l.experience = ?1")
	List<LabOperator> findByExperience(@Param("experience") int experience);
	
	@Query("SELECT l FROM LabOperator l WHERE l.email = ?1")
	Optional<LabOperator> findByEmail(@Param("email") String email);
	
	@Query("SELECT l FROM LabOperator l")
	List<LabOperator> findAllLabOperators();

	




}
