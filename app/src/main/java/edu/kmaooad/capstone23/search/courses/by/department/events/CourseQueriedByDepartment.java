package edu.kmaooad.capstone23.search.courses.by.department.events;

import edu.kmaooad.capstone23.departments.dal.Department;

import java.util.List;

public record CourseQueriedByDepartment (List<Department> queriedDepartments) {}
