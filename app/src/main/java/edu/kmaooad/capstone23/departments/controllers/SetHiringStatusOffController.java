package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.SetHiringStatusOff;
import edu.kmaooad.capstone23.departments.events.HiringStatusSettedOff;
import jakarta.ws.rs.Path;

@Path("/departments/set-hiring-status-off")
public class SetHiringStatusOffController extends TypicalController<SetHiringStatusOff, HiringStatusSettedOff> {
}