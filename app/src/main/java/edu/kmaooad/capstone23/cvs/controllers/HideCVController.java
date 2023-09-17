package edu.kmaooad.capstone23.cvs.controllers;

import edu.kmaooad.capstone23.common.TypicalController;
import edu.kmaooad.capstone23.cvs.commands.*;
import edu.kmaooad.capstone23.cvs.events.*;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/cvs/hide")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = CVHided.class)) })
public class HideCVController extends TypicalController<HideCV, CVHided> {
}
