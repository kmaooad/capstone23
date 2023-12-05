package edu.kmaooad.capstone23.profiles.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.profiles.events.ProfileImageCreate;
import edu.kmaooad.capstone23.profiles.events.ProfileImageCreated;
import jakarta.ws.rs.Path;

@Path("/profile/create-image")
public class ProfileImageController extends TypicalController<ProfileImageCreate, ProfileImageCreated> {}
