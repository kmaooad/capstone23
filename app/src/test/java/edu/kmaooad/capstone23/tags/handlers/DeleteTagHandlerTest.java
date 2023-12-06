package edu.kmaooad.capstone23.tags.handlers;

import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.commands.DeleteTag;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import edu.kmaooad.capstone23.tag.events.TagDeleted;
import edu.kmaooad.capstone23.tag.handlers.DeleteTagHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;


@QuarkusTest
public class DeleteTagHandlerTest {

    @Inject
    DeleteTagHandler deleteTagHandler;

    @Inject
    TagRepository tagRepository;

    @Test
    @DisplayName("Successfully Deletes a Tag")
    public void testDeleteTag() {
        // Arrange
        Tag tag = new Tag();
        tag.tagName = "Tag to Delete";
        tagRepository.persist(tag);
        DeleteTag command = new DeleteTag();
        command.setId(tag.id.toString());

        // Act
        Result<TagDeleted> result = deleteTagHandler.handle(command);

        // Assert
        Assertions.assertTrue(result.isSuccess(), "Tag deletion should be successful");
        Assertions.assertNotNull(result.getValue(), "Result value should not be null");
        Assertions.assertFalse(result.getValue().getId().isEmpty(), "Deleted tag ID should not be empty");
        Assertions.assertFalse(tagRepository.findByIdOptional(tag.id).isPresent(),
                "Tag should no longer exist in the repository after deletion");
    }

    @Test
    @DisplayName("Fails to Delete a Nonexistent Tag")
    public void testDeleteNonexistentTag() {
        // Arrange
        DeleteTag command = new DeleteTag();
        command.setId("123456789101112131415161");

        // Act
        Result<TagDeleted> result = deleteTagHandler.handle(command);

        // Assert
        Assertions.assertFalse(result.isSuccess(), "Deletion should fail for a nonexistent tag");
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode(), "Error code should indicate validation failure");
        Assertions.assertEquals("Tag with this ID does not exist", result.getMessage(), "Error message should indicate nonexistence of tag");
    }

    @AfterEach
    void teardown() {
        tagRepository.deleteAll();
    }
}

