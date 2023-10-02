package edu.kmaooad.capstone23.tag.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.commands.UpdateTag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.events.TagUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateTagHandlerTests {

    @Inject
    TagRepository tagRepository;
    @Inject
    UpdateTagHandler updateTagHandler;

    @Test
    @DisplayName("Update Tag : Basic")
    public void testGroupNameUpdate() {
        var tag = new Tag();
        tag.tagName = "testTag";
        tagRepository.persist(tag);

        UpdateTag command = new UpdateTag();
        command.id = tag.id;
        command.tagName = "new tag name";

        Result<TagUpdated> result = updateTagHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().id.isEmpty());
        Assertions.assertFalse(result.getValue().name.isEmpty());
    }

    @Test
    @DisplayName("Update Tag : Name Validation")
    public void testUpdateGroupForInvalidGroupName() {
        var tag = new Tag();
        tag.tagName = "testTag";
        tagRepository.persist(tag);

        UpdateTag command = new UpdateTag();
        command.id = tag.id;
        command.tagName = "";

        Result<TagUpdated> result = updateTagHandler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Update Tag : Non Existent Tag")
    public void testUpdateNonExistentGroup() {
        var tag = new Tag();
        tag.tagName = "testTag";
        tagRepository.persist(tag);

        UpdateTag command = new UpdateTag();
        command.id = ObjectId.get();
        command.tagName = "new tag name";

        Result<TagUpdated> result = updateTagHandler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }

    @AfterEach
    public void teardown() {
        tagRepository.deleteAll();
    }
}
