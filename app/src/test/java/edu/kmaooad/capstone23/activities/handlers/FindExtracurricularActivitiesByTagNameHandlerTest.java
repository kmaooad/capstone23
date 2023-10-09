package edu.kmaooad.capstone23.activities.handlers;

import edu.kmaooad.capstone23.activities.commands.FindExtracurricularByTag;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.activities.events.ExtracurricularFoundByTag;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.tag.dal.Tag;
import edu.kmaooad.capstone23.tag.dal.TagRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class FindExtracurricularActivitiesByTagNameHandlerTest {
    @Inject
    TagRepository tagRepository;
    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @Inject
    FindExtracurricularActivitiesByTagNameHandler extracurricularActivitiesByTagNameHandler;

    @BeforeEach
    public void init() {
        extracurricularActivityRepository.deleteAll();
        tagRepository.deleteAll();

        tagRepository.persist(new Tag("tag1"));
        tagRepository.persist(new Tag("tag2"));
        tagRepository.persist(new Tag("tag3"));
    }

    @AfterEach
    public void teardown() {
        extracurricularActivityRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    public void findOnlyExtracurricularWithGivenTag() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = "Activity 1";
        extracurricularActivity.tags = new ArrayList<>();
        extracurricularActivity.tags.add(new Tag("tag1"));
        extracurricularActivityRepository.persist(extracurricularActivity);
        ExtracurricularActivity extracurricularActivity1 = new ExtracurricularActivity();
        extracurricularActivity1.tags = new ArrayList<>();
        extracurricularActivity1.extracurricularActivityName = "Activity 2";
        extracurricularActivityRepository.persist(extracurricularActivity1);

        FindExtracurricularByTag command = new FindExtracurricularByTag();
        command.setTagName("tag1");

        Result<ExtracurricularFoundByTag> result = extracurricularActivitiesByTagNameHandler.handle(command);

        assertTrue(result.isSuccess());
        ExtracurricularFoundByTag extracurricularFoundByTag = result.getValue();
        assertNotNull(extracurricularFoundByTag);
        assertEquals(1, extracurricularFoundByTag.getExtracurricularActivities().size());
        assertEquals(extracurricularActivity.extracurricularActivityName, extracurricularFoundByTag.getExtracurricularActivities().get(0).extracurricularActivityName);
    }

    @Test
    public void findsByTagIfCourseHasMultipleTags() {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = "Activity 1";
        extracurricularActivity.tags = new ArrayList<>();
        extracurricularActivity.tags.add(new Tag("tag1"));
        extracurricularActivity.tags.add(new Tag("tag2"));
        extracurricularActivityRepository.persist(extracurricularActivity);
        ExtracurricularActivity extracurricularActivity1 = new ExtracurricularActivity();
        extracurricularActivity1.tags = new ArrayList<>();
        extracurricularActivity1.extracurricularActivityName = "Activity 2";
        extracurricularActivity1.tags.add(new Tag("tag2"));
        extracurricularActivityRepository.persist(extracurricularActivity1);

        FindExtracurricularByTag command = new FindExtracurricularByTag();
        command.setTagName("tag1");

        Result<ExtracurricularFoundByTag> result = extracurricularActivitiesByTagNameHandler.handle(command);

        assertTrue(result.isSuccess());
        ExtracurricularFoundByTag extracurricularFoundByTag = result.getValue();
        assertNotNull(extracurricularFoundByTag);
        assertEquals(1, extracurricularFoundByTag.getExtracurricularActivities().size());
        assertEquals(extracurricularActivity.extracurricularActivityName, extracurricularFoundByTag.getExtracurricularActivities().get(0).extracurricularActivityName);
    }

    @Test
    public void notFoundIfTagNameDoesNotExist() {
        FindExtracurricularByTag command = new FindExtracurricularByTag();
        command.setTagName("null");
        Result<ExtracurricularFoundByTag> result = extracurricularActivitiesByTagNameHandler.handle(command);
        Assertions.assertFalse(result.isSuccess());
    }
}
