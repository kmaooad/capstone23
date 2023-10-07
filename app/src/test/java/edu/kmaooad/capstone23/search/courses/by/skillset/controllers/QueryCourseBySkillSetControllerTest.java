package edu.kmaooad.capstone23.search.courses.by.skillset.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
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
class QueryCourseBySkillSetControllerTest {
    @Inject
    CourseRepository courseRepository;

    @Inject
    SkillSetRepository skillSetRepository;

    @Inject
    RelationRepository relationRepository;


    private List<Course> defaultCourses;
    private List<SkillSet> defaultSkillSets;
    private List<Relation> defaultRelations;

    private void testForSkillSetIndex(int skillSetToFindIndex) {
        assertTrue(skillSetToFindIndex < defaultSkillSets.size());

        var idOfSkillSetToFindBy = defaultSkillSets.get(skillSetToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfSkillSetToFindBy.toHexString());

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/skillset")
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

            var manuallyFoundCourses = manualSearch(skillSetToFindIndex);

            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        testForSkillSetIndex(0);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {
        testForSkillSetIndex(2);
    }

    private List<Course> manualSearch(int skillSetIndex) {
        var skillSet = defaultSkillSets.get(skillSetIndex);
        var skillSetId = skillSet.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(skillSetId))
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
        addDefaultSkillSets();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultSkillSets();
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

    private void addDefaultSkillSets() {
        defaultSkillSets = new ArrayList<>();

        defaultSkillSets.add(createSkillSet("skillSet1"));
        defaultSkillSets.add(createSkillSet("skillSet2"));
        defaultSkillSets.add(createSkillSet("skillSet3"));
        defaultSkillSets.add(createSkillSet("skillSet4"));
        defaultSkillSets.add(createSkillSet("skillSet5"));

        defaultSkillSets = defaultSkillSets.stream()
                .map(skillSetRepository::insert)
                .toList();
    }

    private static SkillSet createSkillSet(String name) {
        var skillSet = new SkillSet();
        skillSet.name = name;
        skillSet.skillIds = List.of(new ObjectId("5f7e47fc8e1f7112d73c92a1"));
        return skillSet;
    }

    private void removeDefaultSkillSets() {
        defaultSkillSets.forEach(skillSetRepository::delete);
        defaultSkillSets = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var skillSetsCount = 5;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultSkillSets.size(), skillSetsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (courseIndex, skillSetIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var skillSetId = defaultSkillSets.get(skillSetIndex).id;
            var relation = new Relation(courseId, skillSetId);

            var optCreatedRelation = relationRepository.createRelation("courses", "skill sets", relation);
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
                .deleteRelation("courses", "skill sets", r.getId()));
        defaultSkillSets = new ArrayList<>();
    }
}