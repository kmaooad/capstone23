package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.orgs.events.HiringStatusSettedOn;
import jakarta.ws.rs.Path;

@Path("/orgs/set-hiring-status-on")
public class SetHiringStatusOnController extends TypicalController<SetHiringStatusOn, HiringStatusSettedOn> {
}
