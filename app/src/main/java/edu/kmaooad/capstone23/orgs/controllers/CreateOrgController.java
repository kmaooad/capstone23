package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import jakarta.ws.rs.Path;

@Path("/orgs/create")
public class CreateOrgController extends TypicalController<CreateOrg, OrgCreated> {


}
