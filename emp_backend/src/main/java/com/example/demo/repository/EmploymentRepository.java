package com.example.demo.repository;

import com.example.demo.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long>{


    List<Employment> findByEmployeeIdOrderByJoiningDateDesc(String employeeId);

     void deleteAllByJoiningDate(LocalDate employeeId);

}
