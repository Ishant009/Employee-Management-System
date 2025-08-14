package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@Table(name="employment")
public class Employment {

	public Employment() {}

	public Employment( String remarks, String office, String post,
                      LocalDate joiningDate) {
		super();
		this.post = post;
		this.office = office;
		this.joiningDate = joiningDate;
		this.remarks = remarks;
	}

//	@Id
//	@Column(name = "history_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long historyId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long historyId;

	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	@JsonBackReference
	private Employee employee;

	@Column(name="office")
	private String office;
	
	@Column(name="post")
	private String post;
	
	@Column(name="jd")
	private LocalDate joiningDate;

	@Column(name="remarks")
	private String remarks;

	
}
