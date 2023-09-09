package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import jakarta.ws.rs.Path;

@Path("/departments/create")
public class CreateDepartmentController extends TypicalController<CreateDepartment, DepartmentCreated> {
}
