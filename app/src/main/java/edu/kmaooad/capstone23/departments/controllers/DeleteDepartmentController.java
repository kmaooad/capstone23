package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.DeleteDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentDeleted;
import jakarta.ws.rs.Path;

@Path("/departments/delete")
public class DeleteDepartmentController extends TypicalController<DeleteDepartment, DepartmentDeleted> {
}

