package edu.kmaooad.capstone23.search.courses.by.skill.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Skill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
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
class QueryCourseBySkillControllerTests {

    @Inject
    CourseRepository courseRepository;

    @Inject
    SkillsRepository skillsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<Skill> defaultSkills;
    private List<Relation> defaultRelations;

    private void testForSkillIndex(int skillToFindIndex) {
        assertTrue(skillToFindIndex < defaultSkills.size());

        var idOfSkillToFindBy = defaultSkills.get(skillToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfSkillToFindBy.toHexString());

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/skill")
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

            var manuallyFoundCourses = manualSearch(skillToFindIndex);
            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query by Skill")
    public void basicQueryTest() {
        testForSkillIndex(0);
    }

    @Test
    @DisplayName("Empty Query by Skill")
    public void emptyQueryTest() {
        testForSkillIndex(2);
    }

    private List<Course> manualSearch(int skillIndex) {
        var skill = defaultSkills.get(skillIndex);
        var skillId = skill.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(skillId))
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
        return subset.stream()
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
        addDefaultSkills();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultSkills();
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

    private void addDefaultSkills() {
        defaultSkills = new ArrayList<>();
        defaultSkills.add(createSkill("skill1"));
        defaultSkills.add(createSkill("skill2"));
        defaultSkills.add(createSkill("skill3"));
        defaultSkills = defaultSkills.stream()
                .map(skillsRepository::insert)
                .toList();
    }

    private static Skill createSkill(String name) {
        var skill = new Skill();
        skill.name = name;
        return skill;
    }

    private void removeDefaultSkills() {
        defaultSkills.forEach(skillsRepository::delete);
        defaultSkills = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var skillsCount = 3;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultSkills.size(), skillsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (courseIndex, skillIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var skillId = defaultSkills.get(skillIndex).id;
            var relation = new Relation(courseId, skillId);

            var optCreatedRelation = relationRepository.createRelation("courses", "skills", relation);
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
                .deleteRelation("courses", "skills", r.getId()));
        defaultSkills = new ArrayList<>();
    }

}
