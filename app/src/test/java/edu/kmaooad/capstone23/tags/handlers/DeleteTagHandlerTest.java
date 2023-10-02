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
    @DisplayName("Delete Tag: Basic")
    public void testDeleteTag() {
        Tag tag = new Tag();
        tag.tagName = "Tag to Delete";
        tagRepository.persist(tag);
        DeleteTag command = new DeleteTag();
        command.setId(tag.id.toString());

        Result<TagDeleted> result = deleteTagHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getId().isEmpty());
        Optional<Tag> deletedTagOptional = tagRepository.findByIdOptional(tag.id);
        Assertions.assertFalse(deletedTagOptional.isPresent());
    }

    @Test
    @DisplayName("Delete Tag: Nonexistent")
    public void testDeleteNonexistentTag() {
        DeleteTag command = new DeleteTag();
        command.setId("123456789101112131415161");

        Result<TagDeleted> result = deleteTagHandler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
        Assertions.assertEquals("Tag with this ID does not exist", result.getMessage());
    }

    @AfterEach
    void teardown() {
        tagRepository.deleteAll();
    }
}