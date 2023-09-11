package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.DeleteExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.ExtracurricularActivityDeleted;
import edu.kmaooad.capstone23.common.TypicalController;
import jakarta.ws.rs.Path;

@Path("/extracurricularActivity/delete")
public class DeleteExtracurricularActivityHandler extends TypicalController<DeleteExtracurricularActivity, ExtracurricularActivityDeleted> {
}
