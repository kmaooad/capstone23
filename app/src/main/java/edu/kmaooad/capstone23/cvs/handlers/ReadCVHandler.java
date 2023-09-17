package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.ReadCV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVRead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


@RequestScoped
public class ReadCVHandler implements CommandHandler<ReadCV, CVRead> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVRead> handle(ReadCV command) {
        CVRead res;
        if (command.getStatus() != null)
            res = new CVRead(cvRepository.find("status", command.getStatus()).stream().toList());
        else
            res = new CVRead(cvRepository.findAll().stream().toList());
        return new Result<>(res);
    }
}
