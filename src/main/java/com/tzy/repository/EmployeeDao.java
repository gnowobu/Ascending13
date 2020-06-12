package com.tzy.repository;

import com.tzy.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee findById(long id);

    List<Employee> getEmployee();

    boolean delete(Employee employee);

    void save(Employee employee);
}
