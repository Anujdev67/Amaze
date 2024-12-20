package com.hexaware.amazecare.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.amazecare.dto.ResponseMessageDto;
import com.hexaware.amazecare.enums.Appointment_Status;
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Appointment;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.LabImage;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.model.SimpleReport;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.AppointmentService;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.ImageService;
import com.hexaware.amazecare.service.LabOperatorService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.ReportService;
import com.hexaware.amazecare.service.SimpleReportService;
import com.hexaware.amazecare.service.TestAndScansService;
import com.hexaware.amazecare.service.UserService;


@RestController
@RequestMapping("/laboperator")
@CrossOrigin(origins = "http://localhost:4200")
public class LabOperatorController {
	@Autowired
	private LabOperatorService labOperatorService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private TestAndScansService testAndScansService;
	@Autowired
    private ReportService reportService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private SimpleReportService simpleReportService;
	@Autowired
    private AppointmentService appointmentService;
	
	Logger logger = LoggerFactory.getLogger(LabOperatorController.class);

	@PostMapping("/onboard")
	public LabOperator onboardOperator(@RequestBody LabOperator labOperator) {
		User user = new User();
		user.setUsername(labOperator.getUser().getUsername());
		user.setPassword(labOperator.getUser().getUsername());
		user.setRole(labOperator.getUser().getRole());
		user = userService.insert(user);
		labOperator.setUser(user);
		labOperator.setJoinedOn(LocalDate.now());
		return labOperatorService.getOperator(labOperator);
	}
	// get a LabOperator by ID
	@GetMapping("/{id}")
	public ResponseEntity<LabOperator> getLabOperatorById(@PathVariable int id) throws ResourceNotFoundException{ // have added throws for any exception
		LabOperator labOperator = labOperatorService.validate(id);
		return ResponseEntity.ok(labOperator);
	}
	//update labop by id
    @PutMapping("/{id}")
    public ResponseEntity<LabOperator> updateLabOperatorById(@PathVariable int id, @RequestBody LabOperator labOperatorDetails) throws ResourceNotFoundException {
        try {
            LabOperator updatedLabOperator = labOperatorService.updateLabOperator(id, labOperatorDetails);
            return ResponseEntity.ok(updatedLabOperator);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/simple-report/create")
    public ResponseEntity<SimpleReport> createSimpleReport(
            @RequestParam int doctorId,
            @RequestParam int patientId,
            @RequestParam String description) {
        SimpleReport report = simpleReportService.generateReport(doctorId, patientId, description);
        return ResponseEntity.ok(report);
    }

    // Endpoint to fetch all SimpleReports
    @GetMapping("/simple-report/all")
    public ResponseEntity<List<SimpleReport>> getAllSimpleReports() {
        List<SimpleReport> reports = simpleReportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    // Endpoint to fetch a specific SimpleReport by ID
    @GetMapping("/simple-report/{id}")
    public ResponseEntity<SimpleReport> getSimpleReportById(@PathVariable int id) throws ResourceNotFoundException {
        SimpleReport report = simpleReportService.getReportById(id);
        return ResponseEntity.ok(report);
    }
    @PutMapping("/appointments/{id}/status")
    public ResponseEntity<Appointment> updateAppointmentStatus(@PathVariable int id, @RequestBody Appointment_Status status) throws ResourceNotFoundException {
        Appointment updatedAppointment = appointmentService.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(updatedAppointment);
    }

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLabOperator(@PathVariable int id){
		try {
			labOperatorService.deleteLabOperator(id);
			return ResponseEntity.ok().build();
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/all")
	public ResponseEntity<List<LabOperator>> getAllLabOperators(){
		List<LabOperator> labOperators = labOperatorService.findAll();
		return ResponseEntity.ok(labOperators);
		
	}

			
	@PostMapping("/generateReport/{lid}/{pid}/{did}/{tid}")
    public ResponseEntity<?> generateReport(@RequestBody Report report, @PathVariable int lid, @PathVariable int pid, @PathVariable int did, @PathVariable int tid, ResponseMessageDto dto) {
		Doctor doctor=null;
	    try {
	    	doctor=doctorService.validate(did);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    Patient patient=null;
	    try {
	    	patient=patientService.validate(pid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    TestAndScans testAndScans=null;
	    try {
	    	testAndScans=testAndScansService.validate(tid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    LabOperator labOperator=null;
	    try {
	    	labOperator=labOperatorService.validate(lid);
	    }catch(ResourceNotFoundException e) {
	    	 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
	    }
	    report.setDoctor(doctor);
	    report.setPatient(patient);
	    report.setLabOperator(labOperator);
	    report.setTestAndScans(testAndScans);
	    report.setGeneratedOn(LocalDate.now());
	    report.setScanTestType(testAndScans.getType());
	    report=reportService.addReport(report);
	    return ResponseEntity.ok(report);
	}
	@PostMapping("/generateSimpleReport/{patientId}/{doctorId}")
	public ResponseEntity<Report> generateSimpleReport(
	        @PathVariable int patientId,
	        @PathVariable int doctorId) {
	    try {
	        // Fetch patient from DB
	        Patient patient = patientService.findById(patientId);
	        if (patient == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        // Fetch doctor from DB
	        Doctor doctor = doctorService.findById(doctorId);
	        if (doctor == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        // Fetch a LabOperator (e.g., the first available one for simplicity)
	        LabOperator labOperator = labOperatorService.findAll().stream().findFirst().orElse(null);
	        if (labOperator == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        // Fetch a test and scan (e.g., the first one related to the patient for simplicity)
	        TestAndScans testAndScans = patient.getTestsAndScans().stream().findFirst().orElse(null);
	        if (testAndScans == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        // Create and save the report
	        Report report = new Report();
	        report.setScanTestType(testAndScans.getType());
	        report.setDescription("Generated automatically");
	        report.setGeneratedOn(LocalDate.now());
	        report.setPatient(patient);
	        report.setDoctor(doctor);
	        report.setLabOperator(labOperator);
	        report.setTestAndScans(testAndScans);

	        Report savedReport = reportService.save(report);
	        return ResponseEntity.ok(savedReport);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

//	 @PostMapping("/generateReport/{lid}/{pid}/{did}/{tid}")
//	    public ResponseEntity<?> generateReport(
//	            @RequestBody Report report,
//	            @PathVariable int lid,
//	            @PathVariable int pid,
//	            @PathVariable int did,
//	            @PathVariable int tid) {
//	        ResponseMessageDto dto = new ResponseMessageDto();
//	        try {
//	            Doctor doctor = doctorService.validate(did);
//	            Patient patient = patientService.validate(pid);
//	            TestAndScans testAndScans = testAndScansService.validate(tid);
//	            LabOperator labOperator = labOperatorService.validate(lid);
//
//	            report.setDoctor(doctor);
//	            report.setPatient(patient);
//	            report.setLabOperator(labOperator);
//	            report.setTestAndScans(testAndScans);
//	            report.setGeneratedOn(LocalDate.now());
//	            report.setScanTestType(testAndScans.getType());
//	            Report savedReport = reportService.addReport(report);
//
//	            return ResponseEntity.ok(savedReport);
//
//	        } catch (ResourceNotFoundException e) {
//	            dto.setMsg(e.getMessage());
//	            return ResponseEntity.badRequest().body(dto);
//	        }
//	    }
//	@PostMapping("/generateReport/{lid}/{pid}/{did}/{tid}")
//	public ResponseEntity<?> generateReport(
//	        @RequestBody Report report,
//	        @PathVariable int lid,
//	        @PathVariable int pid,
//	        @PathVariable int did,
//	        @PathVariable int tid) {
//	    try {
//	        // Validate the LabOperator, Doctor, Patient, and Test entities
//	        LabOperator labOperator = labOperatorService.validate(lid);  // Validate lab operator
//	        Patient patient = patientService.validate(pid); // Validate patient
//	        Doctor doctor = doctorService.validate(did);  // Validate doctor
//	        TestAndScans test = testAndScansService.validate(tid);  // Validate test
//
//	        // Create a new report, associating validated entities
//	        Report newReport = reportService.generateReport(labOperator, patient, doctor, test, report);
//
//	        return ResponseEntity.ok(newReport); // Return the created report object
//	    } catch (Exception e) {
//	        // Handle exceptions appropriately
//	        return ResponseEntity.badRequest().body("Error generating report: " + e.getMessage());
//	    }
//	}


//	 @PostMapping("/report/generate")
//	    public Report generateReport(@RequestBody Report report) {
//	        return reportService.generateReport(report);
//	    }

	    @PostMapping("/image/upload/{reportId}")
	    public LabImage uploadLabImage(@PathVariable int reportId, @RequestParam("file") MultipartFile file) 
	            throws IOException, ResourceNotFoundException {
	        return imageService.uploadImage(reportId, file);
	    }
	    @GetMapping("/report/{id}")
	    public ResponseEntity<Report> getReportById(@PathVariable int id) {
	        try {
	            Report report = reportService.getReportById(id);
	            return ResponseEntity.ok(report);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
	//Pagination to get LAbOp
	@GetMapping("/all/pageable")
	public Page<LabOperator> getAllLabOperators(@RequestParam int page,@RequestParam int size){
		Pageable pageable = PageRequest.of(page, size);
		return labOperatorService.getAllLabOperators(pageable);
	}
	
	
	
// test and scans api
	  @PostMapping("/testandscans/insert")
	    public ResponseEntity<TestAndScans> insertTestAndScans(@RequestBody TestAndScans testAndScans) {
	        TestAndScans createdTestAndScans = testAndScansService.insert(testAndScans);
	        return ResponseEntity.ok(createdTestAndScans);
	    }

	    @GetMapping("/testandscans/{id}")
	    public ResponseEntity<TestAndScans> getTestAndScansById(@PathVariable int id) throws ResourceNotFoundException {
	        TestAndScans testAndScans = testAndScansService.validate(id);
	        return ResponseEntity.ok(testAndScans);
	    }

	    @PutMapping("/testandscans/{id}")
	    public ResponseEntity<TestAndScans> updateTestAndScans(@PathVariable int id, @RequestBody TestAndScans testAndScansDetails) throws ResourceNotFoundException {
	        TestAndScans updatedTestAndScans = testAndScansService.updateTestAndScans(id, testAndScansDetails);
	        return ResponseEntity.ok(updatedTestAndScans);
	    }

	    @DeleteMapping("/testandscans/{id}")
	    public ResponseEntity<?> deleteTestAndScans(@PathVariable int id) throws ResourceNotFoundException {
	        testAndScansService.deleteTestAndScans(id);
	        return ResponseEntity.ok().build();
	    }
	    	//this was earlier
//	    @GetMapping("/testandscans/all")
//	    public ResponseEntity<List<TestAndScans>> getAllTestAndScans() {
//	        List<TestAndScans> testAndScansList = testAndScansService.findAll();
//	        return ResponseEntity.ok(testAndScansList);
//	    }
	    @GetMapping("/testandscans/all")
	    public Page<TestAndScans> getAllTestAndScans(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "7") int size) {
	        
	        Pageable pageable = PageRequest.of(page, size);
	        return testAndScansService.findAll(pageable);
	    }
//	    @GetMapping("/testandscans/all")
//		public Page<TestAndScans> getAllTestAndScans(
//						@RequestParam(required = false, defaultValue = "0") String page, 
//						@RequestParam(required = false, defaultValue = "1000000") String size) 
//								throws Exception{
//			Pageable pageable = null; 
//			
//			try {
//				pageable =   PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
//			}
//			catch(Exception e) {
//				throw e; 
//			}
//			
//			Page<TestAndScans> list =  testAndScansService.findAll(pageable);
//			return list; 
//		}
	    
	    @GetMapping("/patient/all")
	    public List<Patient> getAllPatient() {
	    	return patientService.getAll();
	       
	    }
	    @GetMapping("/testandscans/patients")
	    public ResponseEntity<List<TestAndScans>> getTestsByPatientName(@RequestParam String patientName) {
	        List<TestAndScans> tests = testAndScansService.findByPatientName(patientName);
	        return ResponseEntity.ok(tests);
	    }

	    @GetMapping("/testandscans/doctors")
	    public ResponseEntity<List<TestAndScans>> getTestsByDoctorName(@RequestParam String doctorName) {
	        List<TestAndScans> tests = testAndScansService.findByDoctorName(doctorName);
	        return ResponseEntity.ok(tests);
	    }
	    @PostMapping("/upload/lab/image/{id}")
	    public LabImage uploadImage(@PathVariable int id, @RequestParam MultipartFile file) throws IOException, ResourceNotFoundException {
	        return imageService.uploadImage(id, file);
	    }

	    @GetMapping("/get/lab/image/{id}")
	    public List<LabImage> getImage(@PathVariable int id) {
	        return imageService.find().stream()
	                .filter(image -> image.getReport().getId() == id)
	                .toList();
	    }
}
