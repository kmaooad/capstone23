package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.RelateJobToOrg;
import edu.kmaooad.capstone23.orgs.events.JobToOrgRelated;
import jakarta.ws.rs.Path;

@Path("/orgs/relate-job-to-department")
public class RelateJobToDepartmentController extends TypicalController<RelateJobToOrg, JobToOrgRelated> {
}
