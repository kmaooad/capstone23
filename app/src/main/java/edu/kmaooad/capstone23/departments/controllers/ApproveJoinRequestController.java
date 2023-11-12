package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.ApproveJoinRequest;
import edu.kmaooad.capstone23.departments.events.RequestApproved;
import jakarta.ws.rs.Path;
@Path("/departments/approve-request")
public class ApproveJoinRequestController extends TypicalController<ApproveJoinRequest, RequestApproved> {
}