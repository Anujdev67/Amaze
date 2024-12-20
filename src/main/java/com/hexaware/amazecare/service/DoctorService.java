package com.hexaware.amazecare.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.enums.Appointment_Status;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Appointment;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.repository.AppointmentRepository;
import com.hexaware.amazecare.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	public Doctor insert(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public Doctor validate(int docId) throws ResourceNotFoundException {
		Optional<Doctor>optional=doctorRepository.findById(docId);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Patient Id Given");
		}
		Doctor doctor=optional.get();
		return doctor;
	}

	public List<Appointment> getAllAppointments(int did) {
		Appointment_Status as=Appointment_Status.valueOf("BOOKED");
		return appointmentRepository.getAllAppointmentsByDoctor(as,did);
	}



	public Doctor findById(int id) {
	    return doctorRepository.findById(id).orElse(null);
	}


}
