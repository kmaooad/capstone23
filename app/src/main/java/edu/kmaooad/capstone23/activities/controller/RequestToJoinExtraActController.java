package edu.kmaooad.capstone23.activities.controller;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.events.RequestCreated;
import edu.kmaooad.capstone23.activities.commands.RequestToJoinExtraAct;
import jakarta.ws.rs.Path;

@Path("/extracurricularActivity/request")
public class RequestToJoinExtraActController extends TypicalController<RequestToJoinExtraAct, RequestCreated> {
}
