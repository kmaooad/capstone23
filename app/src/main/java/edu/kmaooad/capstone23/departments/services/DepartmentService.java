package edu.kmaooad.capstone23.departments.services;

import edu.kmaooad.capstone23.departments.dal.Department;

public interface DepartmentService {
    Department createDepartment(String name, String description, String parent);
     Department getDepartmentByName(String name);
     Department getDepartmentById(String id);
     void deleteDepartment(Department department);
     void updateDepartment(Department department);
     void deleteAllDepartments();
}
