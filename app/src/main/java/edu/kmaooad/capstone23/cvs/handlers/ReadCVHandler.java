package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.ReadCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVRead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;


@RequestScoped
public class ReadCVHandler implements CommandHandler<ReadCV, CVRead> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVRead> handle(ReadCV command) {
        List<CV> cvs = cvRepository.find("visibility", "VISIBLE").stream().toList();
        if (command.getStatus() != null)
            cvs = cvs.stream().filter(x -> x.status.equals(command.getStatus())).toList();

        if (command.getIndustry() != null)
            cvs = cvs.stream()
                    .filter(x -> x.preference != null && x.preference.industry.equals(command.getIndustry()))
                    .toList();

        if (command.getLocation() != null)
            cvs = cvs.stream()
                    .filter(x -> x.preference != null && x.preference.location.equals(command.getLocation()))
                    .toList();

        if (command.getCategory() != null)
            cvs = cvs.stream()
                    .filter(x -> x.preference != null && x.preference.category.equals(command.getCategory()))
                    .toList();

        CVRead res = new CVRead(cvs);
        return new Result<>(res);
    }

}
