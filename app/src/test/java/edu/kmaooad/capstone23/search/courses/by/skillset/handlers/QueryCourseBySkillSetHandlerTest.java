package edu.kmaooad.capstone23.search.courses.by.skillset.handlers;

import edu.kmaooad.capstone23.activities.dal.Course;
import edu.kmaooad.capstone23.activities.dal.CourseRepository;
import edu.kmaooad.capstone23.competences.dal.SkillSet;
import edu.kmaooad.capstone23.competences.dal.SkillSetRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QueryCourseBySkillSetHandlerTest {
    @Inject
    QueryCourseBySkillSetHandler handler;

    @Inject
    CourseRepository courseRepository;

    @Inject
    SkillSetRepository skillSetsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<Course> defaultCourses;
    private List<SkillSet> defaultSkillSets;
    private List<Relation> defaultRelations;

    private void testForSkillSetIndex(int skillSetToFindIndex) {
        assertTrue(skillSetToFindIndex < defaultSkillSets.size());

        var idOfSkillSetToFindBy = defaultSkillSets.get(skillSetToFindIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfSkillSetToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();
        var manuallyFoundCourses = manualSearch(skillSetToFindIndex);

        assertTrue(areEqualSets(foundCourses, manuallyFoundCourses));
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

    @Test
    @DisplayName("Reverted Query")
    public void revertedQueryTest() {
        BiFunction<Integer, Integer, Relation> addRevertedRelation = (courseIndex, skillSetIndex) -> {
            var courseId = defaultCourses.get(courseIndex).id;
            var skillSetId = defaultSkillSets.get(skillSetIndex).id;
            var relation = new Relation(skillSetId, courseId);

            var optCreatedRelation = relationRepository.createRelation("skill sets", "courses", relation);
            assertTrue(optCreatedRelation.isPresent());

            var createdRelation = optCreatedRelation.get();
            defaultRelations.add(createdRelation);

            return createdRelation;
        };

        var skillSetsToFindByIndex = 4;
        var courseToFindIndex = 0;
        addRevertedRelation.apply(courseToFindIndex, skillSetsToFindByIndex);

        var idOfSkillSetToFindBy = defaultSkillSets.get(skillSetsToFindByIndex).id;
        var command = new QueryByIdCommand();
        command.id = idOfSkillSetToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundCourses = event.queriedCourses();

        System.out.println();

        assertEquals(foundCourses.size(), 1);
        assertEquals(foundCourses.get(0).name, defaultCourses.get(courseToFindIndex).name);
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

        defaultSkillSets.add(createSkillSet("SkillSet1"));
        defaultSkillSets.add(createSkillSet("SkillSet2"));
        defaultSkillSets.add(createSkillSet("SkillSet3"));
        defaultSkillSets.add(createSkillSet("SkillSet4"));
        defaultSkillSets.add(createSkillSet("SkillSet5"));

        defaultSkillSets = defaultSkillSets.stream()
                .map(skillSetsRepository::insert)
                .toList();
    }

    private static SkillSet createSkillSet(String name) {
        var skillSet = new SkillSet();
        skillSet.name = name;
        skillSet.skillIds = List.of(new ObjectId("5f7e47fc8e1f7112d73c92a1"));
        return skillSet;
    }

    private void removeDefaultSkillSets() {
        defaultSkillSets.forEach(skillSetsRepository::delete);
        defaultSkillSets = new ArrayList<>();
    }

    private void addDefaultRelations() {
        var coursesCount = 4;
        var skillSetCount = 5;

        assertEquals(defaultCourses.size(), coursesCount);
        assertEquals(defaultSkillSets.size(), skillSetCount);

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