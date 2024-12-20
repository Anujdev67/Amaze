package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.amazecare.model.SimpleReport;

public interface SimpleReportRepository extends JpaRepository<SimpleReport, Integer> {

	SimpleReport save(SimpleReport report);
    
}
