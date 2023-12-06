package edu.kmaooad.capstone23.search.courses.by.group.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
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
public class QueryCourseByGroupHandlerTest {
    @Inject
    QueryCourseByGroupHandler handler;

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
        var command = new QueryByIdCommand();
        command.id = idOfGroupToFindBy;

        var result = handler.handle(command);
        System.out.println(result.getMessage());
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();
        var manuallyFoundCourses = manualSearch(groupToFindIndex);

        assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
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

    @Test
    @DisplayName("Reverted Query")
    public void revertedQueryTest() {
        BiFunction<Integer, Integer, Relation> addRevertedRelation = (courseIndex, groupIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var groupId = defaultGroups.get(groupIndex).id;
            var relation = new Relation(groupId, courseId);

            var optCreatedRelation = relationRepository.createRelation("groups", "courses", relation);
            assertTrue(optCreatedRelation.isPresent());

            var createdRelation = optCreatedRelation.get();
            defaultRelations.add(createdRelation);

            return createdRelation;
        };

        var groupToFindByIndex = 4;
        var courseToFindIndex = 0;
        addRevertedRelation.apply(courseToFindIndex, groupToFindByIndex);

        var idOfGroupToFindBy = defaultGroups.get(groupToFindByIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfGroupToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();

        System.out.println();

        assertEquals(foundCourses.size(), 1);
        assertEquals(foundCourses.get(0).name, defaultCourses.get(courseToFindIndex).name);
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