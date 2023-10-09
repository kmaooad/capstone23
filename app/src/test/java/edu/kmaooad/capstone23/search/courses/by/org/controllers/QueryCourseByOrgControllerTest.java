package edu.kmaooad.capstone23.search.courses.by.org.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
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
class QueryCourseByOrgControllerTest {
    @Inject
    CourseRepository courseRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    RelationRepository relationRepository;


    private List<Course> defaultCourses;
    private List<Org> defaultOrgs;
    private List<Relation> defaultRelations;

    private void testForOrgIndex(int orgToFindIndex) {
        assertTrue(orgToFindIndex < defaultOrgs.size());

        var idOfOrgToFindBy = defaultOrgs.get(orgToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfOrgToFindBy.toHexString());

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/org")
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

            var manuallyFoundCourses = manualSearchByOrg(orgToFindIndex);

            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        testForOrgIndex(0);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {
        testForOrgIndex(2);
    }

    private List<Course> manualSearchByOrg(int orgIndex) {
        var org = defaultOrgs.get(orgIndex);
        var orgId = org.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(orgId))
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
        addDefaultOrg();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultOrgs();
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

    private void addDefaultOrg() {
        defaultOrgs = new ArrayList<>();

        defaultOrgs.add(createOrg("org1", "Industry1", "www.org1.com",
                "Description for org1", "org1.com", true));
        defaultOrgs.add(createOrg("org2", "Industry2", "www.org2.com",
                "Description for org2", "org2.com", true));
        defaultOrgs.add(createOrg("org3", "Industry3", "www.org3.com",
                "Description for org3", "org3.com", false));
        defaultOrgs.add(createOrg("org4", "Industry4", "www.org4.com",
                "Description for org4", "org4.com", true));
        defaultOrgs.add(createOrg("org5", "Industry5", "www.org5.com",
                "Description for org5", "org5.com", false));

        defaultOrgs = defaultOrgs.stream()
                .map(orgsRepository::insert)
                .toList();
    }


    private static Org createOrg(String name, String industry, String website,
                                 String description, String emailDomain, Boolean isActive) {
        var org = new Org();
        org.name = name;
        org.industry = industry;
        org.website = website;
        org.description = description;
        org.emailDomain = emailDomain;
        org.isActive = isActive;

        return org;
    }

    private void removeDefaultOrgs() {
        defaultOrgs.forEach(orgsRepository::delete);
        defaultOrgs = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var orgsCount = 5;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultOrgs.size(), orgsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (courseIndex, orgIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var orgId = defaultOrgs.get(orgIndex).id;
            var relation = new Relation(courseId, orgId);

            var optCreatedRelation = relationRepository.createRelation("courses", "orgs", relation);
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
                .deleteRelation("courses", "orgs", r.getId()));
        defaultOrgs = new ArrayList<>();
    }
}