package edu.kmaooad.capstone23.search.courses.by.department.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
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
class QueryCourseByDepartmentControllerTest {

    @Inject
    CourseRepository courseRepository;

    @Inject
    DepartmentsRepository departmentsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<Department> defaultDepartments;
    private List<Relation> defaultRelations;

    private void testForDepartmentIndex(int departmentToFindIndex) {
        assertTrue(departmentToFindIndex < defaultDepartments.size());

        var idOfDepartmentToFindBy = defaultDepartments.get(departmentToFindIndex).id;

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", idOfDepartmentToFindBy.toHexString());

        var response = given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/search/courses/by/dept")
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

            var manuallyFoundCourses = manualSearch(departmentToFindIndex);
            assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    @DisplayName("Basic Query by Department")
    public void basicQueryTest() {
        testForDepartmentIndex(0);
    }

    @Test
    @DisplayName("Empty Query by Department")
    public void emptyQueryTest() {
        testForDepartmentIndex(2);
    }

    private List<Course> manualSearch(int departmentIndex) {
        var department = defaultDepartments.get(departmentIndex);
        var departmentId = department.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(departmentId))
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
        addDefaultDepartments();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultCourses();
        removeDefaultDepartments();
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

    private void addDefaultDepartments() {
        defaultDepartments = new ArrayList<>();
        defaultDepartments.add(createDepartment("dept1", "desc1", "parent"));
        defaultDepartments.add(createDepartment("dept2", "desc2", "parent"));
        defaultDepartments.add(createDepartment("dept3", "desc3", "parent"));
        defaultDepartments = defaultDepartments.stream()
                .map(departmentsRepository::insert)
                .toList();
    }

    private static Department createDepartment(String name, String description, String parent) {
        var department = new Department();
        department.name = name;
        department.description =description;
        department.parent = parent;
        return department;
    }

    private void removeDefaultDepartments() {
        defaultDepartments.forEach(departmentsRepository::delete);
        defaultDepartments = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var departmentsCount = 3;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultDepartments.size(), departmentsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (courseIndex, departmentIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var departmentId = defaultDepartments.get(departmentIndex).id;
            var relation = new Relation(courseId, departmentId);

            var optCreatedRelation = relationRepository.createRelation("courses", "departments", relation);
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
                .deleteRelation("courses", "departments", r.getId()));
        defaultDepartments = new ArrayList<>();
    }

}
