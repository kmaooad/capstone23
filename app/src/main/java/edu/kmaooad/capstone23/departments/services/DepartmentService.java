package edu.kmaooad.capstone23.departments.services;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class DepartmentService {
    @Inject
    private DepartmentsRepository departmentsRepository;

    public Department createDepartment(String name, String description, String parent) {
        Department department = new Department();
        department.name = name;
        department.description = description;
        department.parent = parent;
        department.members = new ArrayList<>();
        department.jobs = new ArrayList<>();
        return departmentsRepository.insert(department);
    }

    public Department getDepartmentByName(String name) {
        return departmentsRepository.findByName(name);
    }

    public Department getDepartmentById(String id) {
        return departmentsRepository.findById(id);
    }

    public void deleteDepartment(Department department) {
        departmentsRepository.delete(department);
    }

    public void deleteDepartmentById(String id) {
        departmentsRepository.deleteById(new ObjectId(id));
    }

    public void updateDepartment(Department department) {
        departmentsRepository.update(department);
    }


    public void deleteAllDepartments() {
        departmentsRepository.deleteAll();
    }
}
