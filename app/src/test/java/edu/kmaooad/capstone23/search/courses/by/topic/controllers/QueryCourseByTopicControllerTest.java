package edu.kmaooad.capstone23.search.courses.by.topic.controllers;

import edu.kmaooad.capstone23.competences.dal.Topic;
import edu.kmaooad.capstone23.competences.dal.TopicRepository;
import io.quarkus.test.junit.QuarkusTest;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QueryCourseByTopicControllerTest {
    @Inject
    CourseRepository courseRepository;

    @Inject
    TopicRepository topicsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<Topic> defaultTopics;
    private List<Relation> defaultRelations;

    private void testForTopicIndex(int topicToFindIndex) {
        assertTrue(topicToFindIndex < defaultTopics.size());

        var idOfTopicToFindBy = defaultTopics.get(topicToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfTopicToFindBy.toHexString());

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/topic")
                .asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode queriedCoursesNode = rootNode.get("queriedCourses");
            List<Course> foundCourses = new ArrayList<>();

            for (JsonNode courseNode : queriedCoursesNode) {
                Course course = objectMapper.treeToValue(courseNode, Course.class);
                foundCourses.add(course);
            }

            var manuallyFoundCourses = manualSearch(topicToFindIndex);
            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query by Topic")
    public void basicQueryTest() {
        testForTopicIndex(0);
    }

    @Test
    @DisplayName("Empty Query by Topic")
    public void emptyQueryTest() {
        testForTopicIndex(2);
    }

    private List<Course> manualSearch(int topicIndex) {
        var topic = defaultTopics.get(topicIndex);
        var topicId = topic.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(topicId))
                .map(Relation::getFirstObjectId)
                .map(id -> {
                    for (var course : defaultCourses) {
                        if (course.id.equals(id))
                            return course;
                    }
                    return null;
                })
                .toList();
    }

    private boolean isSubset(List<Course> subset, List<Course> set) {
        return subset.stream()
                .map(course -> course.name)
                .map(name -> {
                    for (var course : set) {
                        if (course.name.equals(name))
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

        defaultTopics.forEach(topicsRepository::insert);
    }

    private static Topic createTopic(String name, String parentId) {
        var topic = new Topic();
        topic.name = name;
        topic.parentId = parentId;
        return topic;
    }

    private void removeDefaultTopics() {
        defaultTopics.forEach(topicsRepository::delete);
        defaultTopics = new ArrayList<>();
    }

    private void addDefaultRelations() {
        defaultRelations = new ArrayList<>();

        var addRelation = new BiFunction<Integer, Integer, Relation>() {
            @Override
            public Relation apply(Integer courseIndex, Integer topicIndex) {
                var courseId = defaultCourses.get(courseIndex).id;
                var topicId = defaultTopics.get(topicIndex).id;
                var relation = new Relation(courseId, topicId);

                var optCreatedRelation = relationRepository.createRelation("courses", "topics", relation);
                assertTrue(optCreatedRelation.isPresent());

                var createdRelation = optCreatedRelation.get();
                defaultRelations.add(createdRelation);

                return createdRelation;
            }
        };

        addRelation.apply(0, 0);
        addRelation.apply(1, 0);
        addRelation.apply(1, 1);
    }

    private void removeDefaultRelations() {
        defaultRelations.forEach(r -> relationRepository
                .deleteRelation("courses", "topics", r.getId()));
        defaultTopics = new ArrayList<>();
    }
}