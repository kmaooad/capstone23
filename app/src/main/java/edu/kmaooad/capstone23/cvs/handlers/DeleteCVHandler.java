package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.DeleteCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVDeleted;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import edu.kmaooad.capstone23.common.ErrorCode;

@RequestScoped
public class DeleteCVHandler implements CommandHandler<DeleteCV, CVDeleted> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVDeleted> handle(DeleteCV command) {
        ObjectId id = command.getId();
        CV cv = cvRepository.findById(id);

        if (cv == null) {
            return new Result<>(ErrorCode.EXCEPTION, "CV not found");
        }

        cvRepository.delete(cv);

        return new Result<>(new CVDeleted(cv.id.toHexString()));
    }

}
