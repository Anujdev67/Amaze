package com.hexaware.amazecare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.enums.Appointment_Status;
import com.hexaware.amazecare.model.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("SELECT a FROM Appointment a JOIN a.doctor d WHERE a.status = ?1 AND d.id = ?2")
	List<Appointment> getAllAppointmentsByDoctor(Appointment_Status status, int id);

}
