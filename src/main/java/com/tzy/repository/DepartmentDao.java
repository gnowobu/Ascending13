package com.tzy.repository;

import com.tzy.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> getDepartments();

    void save(Department department);

    boolean delete(Department department);

    Department findByName(String name);

    Department getDepartmentEagerBy(Long id);
}
