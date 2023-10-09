package edu.kmaooad.capstone23.tag.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.commands.UpdateTag;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import edu.kmaooad.capstone23.tag.events.TagUpdated;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UpdateTagHandler implements CommandHandler<UpdateTag, TagUpdated> {

    @Inject
    TagRepository repository;

    public Result<TagUpdated> handle(UpdateTag command) {
        Tag tag = repository.findById(command.id);

        if (tag == null) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Tag with such id doesn't exist");
        }

        tag.tagName = command.tagName;
        repository.update(tag);
        TagUpdated result = new TagUpdated(tag.id.toString(), tag.tagName);
        return new Result<>(result);
    }
}
