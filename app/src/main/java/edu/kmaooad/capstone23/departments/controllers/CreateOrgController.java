package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.CreateDepartment;
import edu.kmaooad.capstone23.departments.events.DepartmentCreated;
import jakarta.ws.rs.Path;

@Path("/departemnts/create")
public class CreateOrgController extends TypicalController<CreateDepartment, DepartmentCreated> {
}
