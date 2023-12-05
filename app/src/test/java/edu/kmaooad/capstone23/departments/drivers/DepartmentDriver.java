package edu.kmaooad.capstone23.departments.drivers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentDriver {

    @Inject
    DepartmentService departmentService;

    public Department createDepartment() {
        departmentService.deleteAllDepartments();
        return departmentService.createDepartment("Department to Delete", "This is a department to be deleted", "NaUKMA");
    }
}
