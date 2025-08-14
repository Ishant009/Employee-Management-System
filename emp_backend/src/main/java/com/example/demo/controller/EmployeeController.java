package com.example.demo.controller;

import com.example.demo.model.Employment;
import com.example.demo.repository.EmploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmploymentRepository employmentRepository;
	
	//get all data
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List <Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}  
	
	
	
	//create 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{	employee.setId(employee.getPhone());
		return employeeRepository.save(employee);
	}
	
	
	// get data by id 
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getByID(@PathVariable String id) {
		Employee employee = employeeRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Employee with id "+id+"does not exists"));
		List<Employment> employments= employmentRepository.findByEmployeeIdOrderByJoiningDateDesc(id);
		employee.setEmploymentHistory(employments);
		return ResponseEntity.ok(employee);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employeesName/{name}")
	public ResponseEntity<List<Employee>> getByName(@PathVariable String name) {
		List<Employee> employee = employeeRepository.findByFnameContainingIgnoreCase(name);
		return ResponseEntity.ok(employee);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employeesOffice/{office}")
	public ResponseEntity<List<Employee>> getByOffice(@PathVariable String office) {
		List<Employee> employee = employeeRepository.findByOfficeContainingIgnoreCase(office);
		return ResponseEntity.ok(employee);
	}
	
	
	//update data 
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping ("/employees/{id}")
	public ResponseEntity<Employee> updateEmployeeByID(@PathVariable String id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Employee with id "+id+"does not exists"));
		employee.setFname(employeeDetails.getFname());
		employee.setPhone(employeeDetails.getPhone());
		employee.setEmail(employeeDetails.getEmail());
		employee.setOffice(employeeDetails.getOffice());
		employee.setPost(employeeDetails.getPost());
		employee.setJoiningDate(employeeDetails.getJoiningDate());
		employee.setSalary(employeeDetails.getSalary());
		employee.setQualification(employeeDetails.getQualification());
		employee.setDob(employee.getDob());
		employee.setAddress(employee.getAddress());
		
		Employee updatedEmployee=employeeRepository.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
}
	
	
	@Transactional
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/employees/{id}")
	public ResponseEntity <Map<String, Boolean> >deleteEmployee(@PathVariable String id){
//		employmentRepository.deleteAllByEmployeeId(id);
		Employee employee = employeeRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Employee with id "+id+"does not exists"));
		
		employeeRepository.delete(employee);
		
		Map<String, Boolean>  response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	
}
	@Transactional
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/employment/joiningDate/{date}")
	public ResponseEntity <Map<String, Boolean>> deleteEmploymentData(@PathVariable String date){
		LocalDate now = LocalDate.parse(date);
		employmentRepository.deleteAllByJoiningDate(now);

		Map<String, Boolean>  response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);

	}
}