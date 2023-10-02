package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.ApproveJoinRequest;
import edu.kmaooad.capstone23.departments.commands.RelateJobToDepartment;
import edu.kmaooad.capstone23.departments.events.JobToDepartmentRelated;
import edu.kmaooad.capstone23.departments.events.RequestApproved;
import jakarta.ws.rs.Path;

@Path("/departments/relate-job-to-department")
public class RelateJobToDepartmentController extends TypicalController<RelateJobToDepartment, JobToDepartmentRelated> {
}