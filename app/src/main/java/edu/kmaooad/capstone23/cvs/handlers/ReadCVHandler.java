package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.cvs.commands.ReadCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVRead;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;


import java.util.stream.Stream;


@RequestScoped
public class ReadCVHandler implements CommandHandler<ReadCV, CVRead> {

    @Inject
    CVRepository cvRepository;

    @Override
    public Result<CVRead> handle(ReadCV command) {
        Stream<CV> cvs = cvRepository.find("visibility", "VISIBLE").stream();
        if (command.getStatus() != null)
            cvs = cvs.filter(
                    x -> x.status.equals(command.getStatus()));

        if (command.getIndustry() != null)
            cvs = cvs.filter(
                    x -> x.preference != null && x.preference.industry.equals(command.getIndustry()));

        if (command.getLocation() != null)
            cvs = cvs.filter(
                    x -> x.preference != null && x.preference.location.equals(command.getLocation()));

        if (command.getCategory() != null)
            cvs = cvs.filter(
                    x -> x.preference != null && x.preference.category.equals(command.getCategory()));

        CVRead res = new CVRead(cvs.toList());
        return new Result<>(res);
    }

}
