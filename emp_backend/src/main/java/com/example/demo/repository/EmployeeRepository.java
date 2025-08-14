package com.example.demo.repository;

import com.example.demo.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

    List<Employee> findByFnameContainingIgnoreCase(String name);
    List<Employee> findByOfficeContainingIgnoreCase(String office);

}
