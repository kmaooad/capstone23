package edu.kmaooad.capstone23.search.courses.by.topic.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QueryCourseByTopicHandlerTest {
    @Inject
    QueryCourseByTopicHandler handler;

    @Inject
    CourseRepository courseRepository;

    @Inject
    TopicRepository topicRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<Topic> defaultTopics;
    private List<Relation> defaultRelations;

    private void testForTopicIndex(int topicToFindIndex) {
        assertTrue(topicToFindIndex < defaultTopics.size());

        var idOfTopicToFindBy = defaultTopics.get(topicToFindIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfTopicToFindBy;

        var result = handler.handle(command);
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();
        var manuallyFoundCourses = manualSearch(topicToFindIndex);

        assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
    }

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        testForTopicIndex(0);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {
        testForTopicIndex(2);
    }

    @Test
    @DisplayName("Reverted Query")
    public void revertedQueryTest() {
        BiFunction<Integer, Integer, Relation> addRevertedRelation = (courseIndex, topicIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var topicId = defaultTopics.get(topicIndex).id;
            var relation = new Relation(topicId, courseId);

            var optCreatedRelation = relationRepository.createRelation("topics", "courses", relation);
            assertTrue(optCreatedRelation.isPresent());

            var createdRelation = optCreatedRelation.get();
            defaultRelations.add(createdRelation);

            return createdRelation;
        };

        var topicsToFindByIndex = 4;
        var courseToFindIndex = 0;
        addRevertedRelation.apply(courseToFindIndex, topicsToFindByIndex);

        var idOfTopicToFindBy = defaultTopics.get(topicsToFindByIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfTopicToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();

        System.out.println();

        assertEquals(foundCourses.size(), 1);
        assertEquals(foundCourses.get(0).name, defaultCourses.get(courseToFindIndex).name);
    }

    private List<Course> manualSearch(int topicIndex) {
        var topic = defaultTopics.get(topicIndex);
        var topicId = topic.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(topicId))
                .map(Relation::getFirstObjectId)
                .map(id -> {
                    for(var course : defaultCourses) {
                        if(course.id.equals(id))
                            return course;
                    }
                    return null;
                })
                .toList();
    }

    private boolean isSubset(List<Course> subset, List<Course> set) {
        return subset
                .stream()
                .map(course -> course.name)
                .map(name -> {
                    for(var course : set) {
                        if(course.name.equals(name))
                            return true;
                    }
                    return false;
                })
                .reduce(true, Boolean::logicalAnd);
    }

    private boolean areEqualSets(List<Course> set1, List<Course> set2) {
        return isSubset(set1, set2) && isSubset(set2, set1);
    }

    @BeforeEach
    public void setUp() {
        addDefaultCourses();
        addDefaultTopics();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultTopics();
        removeDefaultRelations();
    }

    private void addDefaultCourses() {
        defaultCourses = new ArrayList<>();
        defaultCourses.add(createCourse("course1"));
        defaultCourses.add(createCourse("course2"));
        defaultCourses.add(createCourse("course3"));
        defaultCourses.add(createCourse("course4"));

        defaultCourses = defaultCourses.stream()
                .map(courseRepository::insert)
                .toList();
    }

    private static Course createCourse(String name) {
        Course course = new Course();
        course.name = name;
        return course;
    }

    private void removeDefaultCourses() {
        defaultCourses.forEach(courseRepository::delete);
        defaultCourses = new ArrayList<>();
    }

    private void addDefaultTopics() {
        defaultTopics = new ArrayList<>();

        defaultTopics.add(createTopic("topic1", "someId1"));
        defaultTopics.add(createTopic("topic2", "someId2"));
        defaultTopics.add(createTopic("topic3", "someId3"));
        defaultTopics.add(createTopic("topic4", "someId4"));
        defaultTopics.add(createTopic("topic5", "someId5"));

        defaultTopics.forEach(topicRepository::insert);
    }

    private static Topic createTopic(String name, String parentId) {
        var topic = new Topic();
        topic.name = name;
        topic.parentId = parentId;
        return topic;
    }

    private void removeDefaultTopics() {
        defaultTopics.forEach(topicRepository::delete);
        defaultTopics = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var topicsCount = 5;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultTopics.size(), topicsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (courseIndex, topicIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var topicId = defaultTopics.get(topicIndex).id;
            var relation = new Relation(courseId, topicId);

            var optCreatedRelation = relationRepository.createRelation("courses", "topics", relation);
            assertTrue(optCreatedRelation.isPresent());

            var createdRelation = optCreatedRelation.get();
            defaultRelations.add(createdRelation);

            return createdRelation;
        };

        defaultRelations = new ArrayList<>();

        addRelation.apply(0, 0);
        addRelation.apply(1, 0);
        addRelation.apply(2, 0);

        addRelation.apply(0, 1);
        addRelation.apply(3, 1);
    }

    private void removeDefaultRelations() {
        defaultRelations.forEach(r -> relationRepository
                .deleteRelation("courses", "topics", r.getId()));
        defaultTopics = new ArrayList<>();
    }
}