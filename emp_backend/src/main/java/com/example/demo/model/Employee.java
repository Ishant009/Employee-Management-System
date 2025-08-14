package com.example.demo.model;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
@Table(name="employees_table")
public class Employee {

	
	public Employee() {}
	
	public Employee(String fname, String phone, String email, long salary, String office, String post,
			LocalDate joiningDate) {
		super();
		this.fname = fname;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.office = office;
		this.post = post;
		this.joiningDate = joiningDate;
	}
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	@Column(name="f_name")
	private String fname;

	@Column(name="phone")
	private String phone;

	@Column(name="category")
	private String category;

	@Column(name="qualification")
	private String qualification;
	
	@Column(name="mail")
	private String email;

	@Column(name="address")
	private String address;

	@Column(name="sal")
	private long salary;
	
	@Column(name="office")
	private String office;
	
	@Column(name="post")
	private String post;

	@Column(name="dob")
	private LocalDate dob;
	
	@Column(name="jd")
	private LocalDate joiningDate;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Employment> employmentHistory;

}
