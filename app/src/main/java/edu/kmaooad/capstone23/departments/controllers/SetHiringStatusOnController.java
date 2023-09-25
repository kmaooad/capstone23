package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOn;
import jakarta.ws.rs.Path;

@Path("/departments/set-hiring-status-on")
public class SetHiringStatusOnController extends TypicalController<SetHiringStatusOn, HiringStatusSettedOn> {
}