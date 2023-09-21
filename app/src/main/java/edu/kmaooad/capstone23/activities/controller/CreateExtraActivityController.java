package edu.kmaooad.capstone23.activities.controller;
import edu.kmaooad.capstone23.activities.commands.CreateExtraActivity;
//import edu.kmaooad.capstone23.activities.commands.CreateExtraActivity;
import edu.kmaooad.capstone23.activities.events.ExtraActivityCreated;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
//@Path("/extraActivities/create")
//@APIResponse(responseCode = "200", content = {
//        @Content(mediaType = "application/json", schema = @Schema(implementation = ExtraActivityCreated.class)) })
@Path("/activities/createExtra")
@APIResponse(responseCode = "200", content = {
        @Content(mediaType = "application/json",
                schema = @Schema(implementation = ExtraActivityCreated.class)) })
public class CreateExtraActivityController extends TypicalController<CreateExtraActivity, ExtraActivityCreated> {

}
