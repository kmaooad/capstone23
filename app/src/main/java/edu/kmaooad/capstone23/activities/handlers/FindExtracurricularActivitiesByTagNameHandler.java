package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.FindExtracurricularByTag;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtracurricularFoundByTag;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FindExtracurricularActivitiesByTagNameHandler implements CommandHandler<FindExtracurricularByTag, ExtracurricularFoundByTag> {
    @Inject
    TagRepository tagRepository;
    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @Override
    public Result<ExtracurricularFoundByTag> handle(FindExtracurricularByTag command) {
        Optional<Tag> tag = tagRepository.find("tagName", command.getTagName()).firstResultOptional();
        if (tag.isEmpty())
            return new Result<>(ErrorCode.NOT_FOUND, "Tag with this name could not be found");

        List<ExtracurricularActivity> result = extracurricularActivityRepository.findAllByTag(tag.get());
        return new Result<>(new ExtracurricularFoundByTag(result));
    }
}
