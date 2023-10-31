package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.DeleteCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.events.CVDeleted;
import edu.kmaooad.capstone23.cvs.services.CVService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

@RequestScoped
public class DeleteCVHandler implements CommandHandler<DeleteCV, CVDeleted> {

    @Inject
    CVService cvService;

    @Override
    public Result<CVDeleted> handle(DeleteCV command) {
        ObjectId id = command.getId();
        CV cv = cvService.findById(id);

        if (cv == null) {
            return new Result<>(ErrorCode.EXCEPTION, "CV not found");
        }

        cvService.delete(cv);

        return new Result<>(new CVDeleted(cv.id));
    }

}
