package edu.kmaooad.capstone23.departments.drivers;

import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.Request;
import edu.kmaooad.capstone23.departments.services.DepartmentRequestService;
import edu.kmaooad.capstone23.departments.services.DepartmentService;
import jakarta.inject.Inject;

public class DepartmentDriver {

    @Inject
    DepartmentService departmentService;

    @Inject
    DepartmentRequestService departmentRequestService;

    public Department createDepartment() {
        departmentService.deleteAllDepartments();
        return departmentService.createDepartment("Department to Delete", "This is a department to be deleted", "NaUKMA");
    }

    public Request createRequest() {
        departmentService.deleteAllDepartments();
        departmentRequestService.deleteAllRequests();
        Department department = createDepartment();
        return departmentRequestService.createRequest("user1@ukma.edu", department.id.toString());
    }

    public Department findDepartmentById(String id) {
        return departmentService.getDepartmentById(id);
    }

    public void updateDepartment(Department department) {
        departmentService.updateDepartment(department);
    }

    public Request findRequestById(String id) {
        return departmentRequestService.getRequestById(id);
    }

    public void approveRequest(String id) {
        departmentRequestService.approveRequest(id);
    }
}
