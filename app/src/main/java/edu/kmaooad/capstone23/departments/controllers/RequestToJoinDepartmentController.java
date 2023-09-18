package edu.kmaooad.capstone23.departments.controllers;


import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.RequestToJoinDepartment;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import jakarta.ws.rs.Path;

@Path("/departments/request")
public class RequestToJoinDepartmentController extends TypicalController<RequestToJoinDepartment, RequestCreated> {
}
