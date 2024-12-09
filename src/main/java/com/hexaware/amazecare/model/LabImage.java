package com.hexaware.amazecare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LabImage {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	 private String fileName;
		private String path;
		@ManyToOne
		private Report report;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
		public void setReport(Report report) {
			this.report = report;
		}

		public Report getReport() {
			return report;
		}
		

}
