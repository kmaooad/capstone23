package edu.kmaooad.capstone23.search.courses.by.project.controllers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.Project;
import edu.kmaooad.capstone23.competences.dal.MongoProjectRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
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
class QueryCourseByProjectControllerTests {

    @Inject
    CourseRepository courseRepository;

    @Inject
    MongoProjectRepository projsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<Project> defaultProjects;
    private List<Relation> defaultRelations;

    private void testForProjectIndex(int projectToFindIndex) {
        assertTrue(projectToFindIndex < defaultProjects.size());

        var idOfProjectToFindBy = defaultProjects.get(projectToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfProjectToFindBy);

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/project")
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

            var manuallyFoundCourses = manualSearch(projectToFindIndex);
            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query by Project")
    public void basicQueryTest() {
        testForProjectIndex(0);
    }

    @Test
    @DisplayName("Empty Query by Project")
    public void emptyQueryTest() {
        testForProjectIndex(2);
    }

    private List<Course> manualSearch(int projectIndex) {
        var project = defaultProjects.get(projectIndex);
        var projectId = project.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(projectId))
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
        addDefaultProjects();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultProjects();
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

    private void addDefaultProjects() {
        defaultProjects = new ArrayList<>();
        defaultProjects.add(createProject("project1"));
        defaultProjects.add(createProject("project2"));
        defaultProjects.add(createProject("project3"));
        defaultProjects = defaultProjects.stream()
                .map(projsRepository::insert)
                .toList();
    }

    private static Project createProject(String name) {
        Project project = new Project();
        project.name = name;
        return project;
    }

    private void removeDefaultProjects() {
        defaultProjects.forEach(projsRepository::delete);
        defaultProjects = new ArrayList<>();
    }

    private void addDefaultRelations() {
        defaultRelations = new ArrayList<>();

        var addRelation = new BiFunction<Integer, Integer, Relation>() {
            @Override
            public Relation apply(Integer courseIndex, Integer projectIndex) {
                var courseId = defaultCourses.get(courseIndex).id;
                var projectId = defaultProjects.get(projectIndex).id;
                var relation = new Relation(courseId, new ObjectId(projectId));

                var optCreatedRelation = relationRepository.createRelation("courses", "projects", relation);
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
                .deleteRelation("courses", "projects", r.getId()));
        defaultProjects = new ArrayList<>();
    }

}
