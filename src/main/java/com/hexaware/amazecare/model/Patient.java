package com.hexaware.amazecare.model;

import java.util.List;

import com.hexaware.amazecare.enums.PatientType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private PatientType patientType;
	
	@OneToOne
	private User user;
	@OneToMany 
	private List<TestAndScans> testsAndScans;

	public List<TestAndScans> getTestsAndScans() {
		return testsAndScans;
	}

	public void setTestsAndScans(List<TestAndScans> testsAndScans) {
		this.testsAndScans = testsAndScans;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PatientType getPatientType() {
		return patientType;
	}

	public void setPatientType(PatientType patientType) {
		this.patientType = patientType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", patientType=" + patientType + ", user=" + user + "]";
	}
	

}
