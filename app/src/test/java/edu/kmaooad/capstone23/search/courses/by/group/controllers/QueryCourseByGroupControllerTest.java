package edu.kmaooad.capstone23.search.courses.by.group.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QueryCourseByGroupControllerTest {
    @Inject
    CourseRepository courseRepository;

    @Inject
    GroupsRepository groupsRepository;

    @Inject
    RelationRepository relationRepository;


    private List<Course> defaultCourses;
    private List<Group> defaultGroups;
    private List<Relation> defaultRelations;

    private void testForGroupIndex(int groupToFindIndex) {
        assertTrue(groupToFindIndex < defaultGroups.size());

        var idOfGroupToFindBy = defaultGroups.get(groupToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfGroupToFindBy.toHexString());

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/group")
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

            System.out.println("FOUND COURSES NAMES:");
            for (var course : foundCourses) {
                System.out.println(course.name);
            }

            var manuallyFoundCourses = manualSearch(groupToFindIndex);

            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        testForGroupIndex(0);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {
        testForGroupIndex(2);
    }

    private List<Course> manualSearch(int groupIndex) {
        var group = defaultGroups.get(groupIndex);
        var groupId = group.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(groupId))
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
        addDefaultGroups();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultGroups();
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

    private void addDefaultGroups() {
        defaultGroups = new ArrayList<>();

        defaultGroups.add(createGroup("group1", "someId1"));
        defaultGroups.add(createGroup("group2", "someId2"));
        defaultGroups.add(createGroup("group3", "someId3"));
        defaultGroups.add(createGroup("group4", "someId4"));
        defaultGroups.add(createGroup("group5", "someId5"));

        defaultGroups = defaultGroups.stream()
                .map(groupsRepository::insert)
                .toList();
    }

    private static Group createGroup(String name, String templateId) {
        var group = new Group();
        group.name = name;
        group.templateId = templateId;
        return group;
    }

    private void removeDefaultGroups() {
        defaultGroups.forEach(groupsRepository::delete);
        defaultGroups = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var groupsCount = 5;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultGroups.size(), groupsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (courseIndex, groupIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var groupId = defaultGroups.get(groupIndex).id;
            var relation = new Relation(courseId, groupId);

            var optCreatedRelation = relationRepository.createRelation("courses", "groups", relation);
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
                .deleteRelation("courses", "groups", r.getId()));
        defaultGroups = new ArrayList<>();
    }
}