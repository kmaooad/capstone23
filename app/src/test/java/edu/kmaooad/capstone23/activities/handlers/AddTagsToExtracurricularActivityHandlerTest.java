package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.AddTagsToExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.TagsAddedToExtracurricularActivity;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class AddTagsToExtracurricularActivityHandlerTest {

    @Inject
    AddTagsToExtracurricularActivityHandler addTagsHandler;

    @Inject
    ExtracurricularActivityRepository activityRepository;

    @Inject
    TagRepository tagRepository;

    @AfterEach
    void tearDown() {
        activityRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    @DisplayName("Add Tags to Extracurricular Activity")
    public void testAddTagsToExtracurricularActivity() {
        ExtracurricularActivity activity = new ExtracurricularActivity();
        activity.extracurricularActivityName = "Activity 1";
        activityRepository.persist(activity);
        Tag tag1 = new Tag();
        tag1.tagName = "Tag 1";
        tagRepository.persist(tag1);
        Tag tag2 = new Tag();
        tag2.tagName = "Tag 2";
        tagRepository.persist(tag2);
        List<String> tagIds = Arrays.asList(tag1.id.toString(), tag2.id.toString());

        AddTagsToExtracurricularActivity command = new AddTagsToExtracurricularActivity();
        command.setExtracurricularActivityName(activity.extracurricularActivityName);
        command.setTagIds(tagIds);

        Result<TagsAddedToExtracurricularActivity> result = addTagsHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        TagsAddedToExtracurricularActivity tagsAdded = result.getValue();
        Assertions.assertNotNull(tagsAdded);
        Assertions.assertEquals(activity.extracurricularActivityName, tagsAdded.getActivityName());
        Assertions.assertEquals(2, tagsAdded.getTagNames().size());
        Assertions.assertTrue(tagsAdded.getTagNames().contains("Tag 1"));
        Assertions.assertTrue(tagsAdded.getTagNames().contains("Tag 2"));
        ExtracurricularActivity updatedActivity = activityRepository.findById(activity.id);
        Assertions.assertNotNull(updatedActivity);
        Assertions.assertEquals(2, updatedActivity.tags.size());
    }

    @Test
    @DisplayName("Add Tags to Nonexistent Extracurricular Activity")
    public void testAddTagsToNonexistentExtracurricularActivity() {
        List<String> tagIds = Arrays.asList("123456789101112131415161", "123456789101112131415162");

        AddTagsToExtracurricularActivity command = new AddTagsToExtracurricularActivity();
        command.setExtracurricularActivityName("Nonexistent Activity");
        command.setTagIds(tagIds);

        Result<TagsAddedToExtracurricularActivity> result = addTagsHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.EXCEPTION, result.getErrorCode());
        Assertions.assertEquals("Extracurricular activity not found", result.getMessage());
    }
}
