package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.orgs.commands.RequestToJoinOrg;
import jakarta.ws.rs.Path;

@Path("/activities/request")
public class RequestToJoinExtraAct extends TypicalController<RequestToJoinOrg, RequestCreated> {
}