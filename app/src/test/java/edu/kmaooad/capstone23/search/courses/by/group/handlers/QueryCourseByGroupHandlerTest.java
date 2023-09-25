package edu.kmaooad.capstone23.search.courses.by.group.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.groups.dal.Group;
import edu.kmaooad.capstone23.groups.dal.GroupsRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class QueryCourseByGroupHandlerTest {
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

    @Test
    @DisplayName("Basic Query")
    public void basicQueryTest() {
        var groupToFindIndex = 0;
        assertTrue(groupToFindIndex < defaultGroups.size());

        var idOfGroupToFindBy = defaultGroups.get(groupToFindIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfGroupToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();
        var manuallyFoundCourses = manualSearch(groupToFindIndex);

        assertFalse(foundCourses.isEmpty());
        assertEqualSets(foundCourses, manuallyFoundCourses);
    }

    @Test
    @DisplayName("Empty Query")
    public void emptyQueryTest() {

    }

    @Test
    @DisplayName("Reverted Query")
    public void revertedQueryTest() {

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

    private <T> boolean isSubset(List<T> subset, List<T> set) {
        return subset.stream()
                .map(set::contains)
                .reduce(true, Boolean::logicalAnd);
    }

    private <T> boolean areEqualSets(List<T> set1, List<T> set2) {
        return isSubset(set1, set2) && isSubset(set2, set1);
    }

    private <T> void assertEqualSets(List<T> set1, List<T> set2) {
        assertTrue(areEqualSets(set1, set2));
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
        defaultCourses = new ArrayList<>() {{
            add(new Course(){{ name = "course1"; }});
            add(new Course(){{ name = "course2"; }});
            add(new Course(){{ name = "course3"; }});
            add(new Course(){{ name = "course4"; }});
        }};

        defaultCourses = defaultCourses.stream()
                .map(courseRepository::insert)
                .toList();
    }

    private void removeDefaultCourses() {
        defaultCourses.forEach(courseRepository::delete);
        defaultCourses = new ArrayList<>();
    }

    private void addDefaultGroups() {
        defaultGroups = new ArrayList<>() {{
            add(new Group(){{ name = "group1"; templateId = "someId1"; }});
            add(new Group(){{ name = "group2"; templateId = "someId2"; }});
            add(new Group(){{ name = "group3"; templateId = "someId3"; }});
            add(new Group(){{ name = "group4"; templateId = "someId4"; }});
            add(new Group(){{ name = "group5"; templateId = "someId5"; }});
        }};

        defaultGroups = defaultGroups.stream()
                .map(groupsRepository::insert)
                .toList();
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