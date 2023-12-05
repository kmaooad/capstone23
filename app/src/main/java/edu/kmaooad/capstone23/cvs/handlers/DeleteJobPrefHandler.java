package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.DeleteJobPref;
import edu.kmaooad.capstone23.cvs.services.CVService;
import edu.kmaooad.capstone23.cvs.events.JobPrefDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteJobPrefHandler implements CommandHandler<DeleteJobPref, JobPrefDeleted> {

    @Inject
    CVService cvService;

    @Override
    public Result<JobPrefDeleted> handle(DeleteJobPref command) {
        if (command == null || command.getCvId() == null)  return new Result<>(ErrorCode.EXCEPTION, "Must contain cv id");
        ObjectId id = command.getCvId();
        cvService.deleteById(id);
        return new Result<>(new JobPrefDeleted(true));
    }
}
