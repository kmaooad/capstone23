package edu.kmaooad.capstone23.departments.services;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.search.Listable;

public interface DepartmentService extends Listable<Department> /* implement this, please */ {
    Department createDepartment(String name, String description, String parent);

    Department getDepartmentByName(String name);

    Department getDepartmentById(String id);

    Department findById(String id);

    void deleteDepartment(Department department);

    void updateDepartment(Department department);

    void deleteAllDepartments();
}
