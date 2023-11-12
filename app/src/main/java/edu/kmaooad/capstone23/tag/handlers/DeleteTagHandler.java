package edu.kmaooad.capstone23.tag.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.commands.DeleteTag;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.events.TagDeleted;
import edu.kmaooad.capstone23.tag.services.TagService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.Optional;

@RequestScoped
public class DeleteTagHandler implements CommandHandler<DeleteTag, TagDeleted> {

    @Inject
    TagService tagService;

    public Result<TagDeleted> handle(DeleteTag command) {
        Optional<Tag> tag = tagService.findByIdOptional(new ObjectId(command.getId()));
        if (tag.isEmpty()) {
            return new Result<>(ErrorCode.VALIDATION_FAILED, "Tag with this ID does not exist");
        }

        tagService.delete(tag.get());

        return new Result<>(new TagDeleted(command.getId()));
    }
}
