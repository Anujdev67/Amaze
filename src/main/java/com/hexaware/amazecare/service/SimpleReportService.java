package com.hexaware.amazecare.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.SimpleReport;
import com.hexaware.amazecare.repository.DoctorRepository;
import com.hexaware.amazecare.repository.PatientRepository;
import com.hexaware.amazecare.repository.SimpleReportRepository;

@Service
public class SimpleReportService {

    @Autowired
    private SimpleReportRepository simpleReportRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public SimpleReport generateReport(int doctorId, int patientId, String description) {
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new IllegalArgumentException("Doctor not found with ID: " + doctorId));
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + patientId));

        SimpleReport report = new SimpleReport();
        report.setDescription(description);
        report.setGeneratedDate(LocalDate.now());
        report.setDoctor(doctor);
        report.setPatient(patient);

        return simpleReportRepository.save(report);
    }

	 public List<SimpleReport> getAllReports() {
        return simpleReportRepository.findAll();
    }

    public SimpleReport getReportById(int id) throws ResourceNotFoundException {
        return simpleReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with ID: " + id));
    }
}
