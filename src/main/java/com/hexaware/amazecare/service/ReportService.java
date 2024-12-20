package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.repository.LabOperatorRepository;
import com.hexaware.amazecare.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private LabOperatorRepository labOperatorRepository;

	public Report addReport(Report report) {
		// TODO Auto-generated method stub
		return reportRepository.save(report);
	}

	public Report generateReport(LabOperator labOperator, Patient patient, Doctor doctor, TestAndScans test, Report reportData) {
	    // Create the report object with necessary fields
	    Report report = new Report();
	    report.setLabOperator(labOperator);  // Assign validated LabOperator
	    report.setPatient(patient);  // Assign validated Patient
	    report.setDoctor(doctor);  // Assign validated Doctor
	    report.setTestAndScans(test);  // Assign validated TestAndScans
	    report.setDescription(reportData.getDescription());
	    report.setScanTestType(reportData.getScanTestType());

	    // Add other fields as required (like timestamps, etc.)

	    // Save the report to the database
	    return reportRepository.save(report);
	}


    public Report findById(int id) throws ResourceNotFoundException {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found for ID: " + id));
    }
    public Report getReportById(int id) throws ResourceNotFoundException {
        return reportRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Report not found for this id :: " + id));
    }

	public Report save(Report report) {
		// TODO Auto-generated method stub
		return null;
	}

}
