package com.hexaware.amazecare.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.enums.TestScanStatus;
import com.hexaware.amazecare.model.TestAndScans;



public interface TestAndScanRepository extends JpaRepository<TestAndScans, Integer>{

	List<TestAndScans> findByStatus(TestScanStatus status);
	
	@Query("SELECT t FROM TestAndScans t JOIN t.patient p WHERE p.name = ?1")
    List<TestAndScans> findByPatientName(String patientName);

    @Query("SELECT t FROM TestAndScans t JOIN t.doctor d WHERE d.name = ?1")
    List<TestAndScans> findByDoctorName(String doctorName);


   
}













































//    List<TestAndScans> findAllByDateBetween(LocalDate start, LocalDate end);





//List<TestAndScans> findByTestOrScanPrescibedon(LocalDate date);