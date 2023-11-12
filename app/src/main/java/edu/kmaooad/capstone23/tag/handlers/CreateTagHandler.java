package edu.kmaooad.capstone23.tag.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.commands.CreateTag;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import edu.kmaooad.capstone23.tag.events.TagCreated;
import edu.kmaooad.capstone23.tag.services.TagService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateTagHandler implements CommandHandler<CreateTag, TagCreated> {

    @Inject
    TagService tagService;

    @Override
    public Result<TagCreated> handle(CreateTag command) {
        var tagEntity = new Tag();
        tagEntity.tagName = command.getTagName();
        tagService.persist(tagEntity);
        var tagCreated = new TagCreated(tagEntity.id, tagEntity.tagName);
        return new Result<>(tagCreated);
    }
}
