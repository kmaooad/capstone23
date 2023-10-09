package edu.kmaooad.capstone23.search.courses.by.department.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.departments.dal.Department;
import edu.kmaooad.capstone23.departments.dal.DepartmentsRepository;
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
class QueryCourseByDepartmentHandlerTest {
    @Inject
    QueryCourseByDepartmentHandler handler;

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
        var command = new QueryByIdCommand();
        command.id = idOfDepartmentToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();
        var manuallyFoundCourses = manualSearch(departmentToFindIndex);

        assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
    }

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        testForDepartmentIndex(0);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {
        testForDepartmentIndex(2);
    }

    @Test
    @DisplayName("Reverted Query")
    public void revertedQueryTest() {
        BiFunction<Integer, Integer, Relation> addRevertedRelation = (courseIndex, departmentIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var departmentId = defaultDepartments.get(departmentIndex).id;
            var relation = new Relation(departmentId, courseId);

            var optCreatedRelation = relationRepository.createRelation("departments", "courses", relation);
            assertTrue(optCreatedRelation.isPresent());

            var createdRelation = optCreatedRelation.get();
            defaultRelations.add(createdRelation);

            return createdRelation;
        };

        var departmentToFindByIndex = 4;
        var courseToFindIndex = 0;
        addRevertedRelation.apply(courseToFindIndex, departmentToFindByIndex);

        var idOfDepartmentToFindBy = defaultDepartments.get(departmentToFindByIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfDepartmentToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();

        System.out.println();

        assertEquals(foundCourses.size(), 1);
        assertEquals(foundCourses.get(0).name, defaultCourses.get(courseToFindIndex).name);
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

        defaultDepartments.add(createDepartment("department1", "someId1"));
        defaultDepartments.add(createDepartment("department2", "someId2"));
        defaultDepartments.add(createDepartment("department3", "someId3"));
        defaultDepartments.add(createDepartment("department4", "someId4"));
        defaultDepartments.add(createDepartment("department5", "someId5"));

        defaultDepartments = defaultDepartments.stream()
                .map(departmentsRepository::insert)
                .toList();
    }

    private static Department createDepartment(String name, String id) {
        var department = new Department();
        department.name = name;
        department.description = id;
        department.parent = "Some Parent";
        department.members = new ArrayList<>();
        return department;
    }

    private void removeDefaultDepartments() {
        defaultDepartments.forEach(departmentsRepository::delete);
        defaultDepartments = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var departmentsCount = 5;

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