package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.orgs.commands.RequestToJoinOrg;
import jakarta.ws.rs.Path;

@Path("/orgs/request")
public class RequestToJoinOrgController extends TypicalController<RequestToJoinOrg, RequestCreated> {
}
