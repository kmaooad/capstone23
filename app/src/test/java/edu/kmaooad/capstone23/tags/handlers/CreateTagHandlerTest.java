package edu.kmaooad.capstone23.tags.handlers;

import edu.kmaooad.capstone23.tag.commands.CreateTag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import edu.kmaooad.capstone23.tag.handlers.CreateTagHandler;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class CreateTagHandlerTest {

    @Inject
    TagRepository tagRepository;

    @Inject
    CreateTagHandler createTagHandler;

    @Test
    void testTagCreation() {
      var createTag = new CreateTag();
      createTag.setTagName("Test tagName");

      var tagCreated = createTagHandler.handle(createTag);
      assertTrue(tagCreated.isSuccess());
      assertNotNull(tagCreated.getValue().getObjectId());
      assertEquals(createTag.getTagName(), tagCreated.getValue().getTagName());
    }


    @AfterEach
    public void teardown() {
        tagRepository.deleteAll();
    }
}
