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
import com.hexaware.amazecare.exceptions.ResourceNotFoundException;
import com.hexaware.amazecare.model.Doctor;
import com.hexaware.amazecare.model.LabImage;
import com.hexaware.amazecare.model.LabOperator;
import com.hexaware.amazecare.model.Patient;
import com.hexaware.amazecare.model.Report;
import com.hexaware.amazecare.model.TestAndScans;
import com.hexaware.amazecare.model.User;
import com.hexaware.amazecare.service.DoctorService;
import com.hexaware.amazecare.service.ImageService;
import com.hexaware.amazecare.service.LabOperatorService;
import com.hexaware.amazecare.service.PatientService;
import com.hexaware.amazecare.service.ReportService;
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

	    @GetMapping("/testandscans/all")
	    public ResponseEntity<List<TestAndScans>> getAllTestAndScans() {
	        List<TestAndScans> testAndScansList = testAndScansService.findAll();
	        return ResponseEntity.ok(testAndScansList);
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
