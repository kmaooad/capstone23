package edu.kmaooad.capstone23.departments.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.departments.commands.AddLogo;
import edu.kmaooad.capstone23.departments.events.LogoAdded;
import jakarta.ws.rs.Path;

@Path("/departments/add-logo")
public class AddLogoController extends TypicalController<AddLogo, LogoAdded> {
}