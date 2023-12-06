package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AddTagsToExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.events.TagsAddedToExtracurricularActivity;
import edu.kmaooad.capstone23.activities.services.ExtracurricularActivityService;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.services.TagService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class AddTagsToExtracurricularActivityHandler implements CommandHandler<AddTagsToExtracurricularActivity, TagsAddedToExtracurricularActivity> {

    @Inject
    ExtracurricularActivityService activityService;

    @Inject
    TagService tagService;

    public Result<TagsAddedToExtracurricularActivity> handle(AddTagsToExtracurricularActivity command) {
        String extracurricularActivityName = command.getExtracurricularActivityName();
        ExtracurricularActivity activity = activityService.find(extracurricularActivityName);

        if (activity == null) {
            return new Result<>(ErrorCode.EXCEPTION, "Extracurricular activity not found");
        }

        List<Tag> tagsToAdd = new ArrayList<>();
        if (activity.tags != null) {
            tagsToAdd.addAll(activity.tags);
        }

        for (String tagId : command.getTagIds()) {
            Tag tag = tagService.findById(new ObjectId(tagId));
            if (tag != null && !tagsToAdd.contains(tag)) {
                tagsToAdd.add(tag);
            }
        }

        activity.tags = tagsToAdd;
        activityService.update(activity);

        TagsAddedToExtracurricularActivity result = new TagsAddedToExtracurricularActivity(
                extracurricularActivityName,
                tagsToAdd.stream().map(tag -> tag.tagName).collect(Collectors.toList()));

        return new Result<>(result);
    }
}
