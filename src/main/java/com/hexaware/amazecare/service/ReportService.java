package com.hexaware.amazecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.repository.ReportRepository;

@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;

	public Report addReport(Report report) {
		// TODO Auto-generated method stub
		return reportRepository.save(report);
	}

}
