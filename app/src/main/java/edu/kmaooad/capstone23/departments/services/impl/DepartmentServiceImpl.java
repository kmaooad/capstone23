package edu.kmaooad.capstone23.departments.services.impl;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@RequestScoped
public class DepartmentServiceImpl implements DepartmentService {

    @Inject
    DepartmentsRepository departmentsRepository;

    public Department createDepartment(String name, String description, String parent) {
        Department department = new Department();
        department.name = name;
        department.description = description;
        department.parent = parent;
        department.members = new ArrayList<>();
        department.jobs = new ArrayList<>();
        return departmentsRepository.insert(department);
    }

    @Override
    public Department getDepartmentByName(String name) {
        return departmentsRepository.findByName(name);
    }

    @Override
    public Department getDepartmentById(String id) {
        return departmentsRepository.findById(id);
    }

    @Override
    public Department findById(String id) {
        return departmentsRepository.findById(new ObjectId(id));
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentsRepository.delete(department);
    }

    public void deleteDepartmentById(String id) {
        departmentsRepository.deleteById(new ObjectId(id));
    }

    @Override
    public void updateDepartment(Department department) {
        departmentsRepository.update(department);
    }

    @Override
    public void deleteAllDepartments() {
        departmentsRepository.deleteAll();
    }
}
