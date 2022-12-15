package com.example.NestDigitalApplication.dao;

import com.example.NestDigitalApplication.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee,Integer> {


    @Query(value = "SELECT `id`, `designation`, `email`, `emp_code`, `name`, `password`, `phone`, `username` FROM `employee` WHERE `name` LIKE %:name%", nativeQuery = true)
    List<Employee> SearchEmployee(@Param("name") String name);
}
