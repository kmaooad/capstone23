package edu.kmaooad.capstone23.orgs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.orgs.events.HiringStatusSettedOff;
import jakarta.ws.rs.Path;

@Path("/orgs/set-hiring-status-off")
public class SetHiringStatusOffController extends TypicalController<SetHiringStatusOff, HiringStatusSettedOff> {
}
