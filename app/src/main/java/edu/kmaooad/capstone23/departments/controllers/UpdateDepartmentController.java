package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.UpdateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentUpdated;
import jakarta.ws.rs.Path;

@Path("/departments/update")
public class UpdateDepartmentController extends TypicalController<UpdateDepartment, DepartmentUpdated> {
}
