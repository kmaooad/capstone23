package edu.kmaooad.capstone23.search.extracurricularActivities.by.group.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
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
import java.util.*;
import java.util.function.BiFunction;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QueryExtracurricularActivityByGroupControllerTest {

    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @Inject
    GroupsRepository groupsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<ExtracurricularActivity> defaultExtracurricularActivities;
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
                .post("/search/extracurricularActivity/by/group")
                .asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode queriedExtracurricularActivityNode = rootNode.get("queriedExtracurricularActivities");
            List<ExtracurricularActivity> foundExtracurricularActivity = new ArrayList<>();

            for (JsonNode extracurricularActivityNode : queriedExtracurricularActivityNode) {
                ExtracurricularActivity extracurricularActivity = objectMapper.treeToValue(extracurricularActivityNode, ExtracurricularActivity.class);
                foundExtracurricularActivity.add(extracurricularActivity);
            }

            System.out.println("FOUND COURSES NAMES:");
            for (var extracurricularActivity : foundExtracurricularActivity) {
                System.out.println(extracurricularActivity.extracurricularActivityName);
            }

            var  manuallyFoundExtracurricularActivity = manualSearch(groupToFindIndex);

            assertTrue(areEqualSets(foundExtracurricularActivity, manuallyFoundExtracurricularActivity));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
    private boolean isSubset(List<ExtracurricularActivity> subset, List<ExtracurricularActivity> set) {
        return subset
                .stream()
                .map(extracurricularActivity -> extracurricularActivity.extracurricularActivityName)
                .map(name -> {
                    for(var course : set) {
                        if(course.extracurricularActivityName.equals(name))
                            return true;
                    }
                    return false;
                })
                .reduce(true, Boolean::logicalAnd);
    }

    private boolean areEqualSets(List<ExtracurricularActivity> set1, List<ExtracurricularActivity> set2) {
        return isSubset(set1, set2) && isSubset(set2, set1);
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

    private List<ExtracurricularActivity> manualSearch(int groupIndex) {
        var group = defaultGroups.get(groupIndex);
        var groupId = group.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(groupId))
                .map(Relation::getFirstObjectId)
                .map(id -> {
                    for(var activity : defaultExtracurricularActivities) {
                        if(activity.id.equals(id))
                            return activity;
                    }
                    return null;
                })
                .toList();
    }

    @BeforeEach
    public void setUp() {
        addDefaultExtracurricularActivity();
        addDefaultGroups();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultExtracurricularActivity();
        removeDefaultGroups();
        removeDefaultRelations();
    }

    private void addDefaultExtracurricularActivity() {
        defaultExtracurricularActivities = new ArrayList<>();
        defaultExtracurricularActivities.add(createExtracurricularActivity("ex1"));
        defaultExtracurricularActivities.add(createExtracurricularActivity("ex2"));
        defaultExtracurricularActivities.add(createExtracurricularActivity("ex3"));
        defaultExtracurricularActivities.add(createExtracurricularActivity("ex4"));

        defaultExtracurricularActivities = defaultExtracurricularActivities.stream()
                .map(extracurricularActivityRepository::insert)
                .toList();
    }

    private static ExtracurricularActivity createExtracurricularActivity(String name) {
        ExtracurricularActivity extracurricularActivity = new ExtracurricularActivity();
        extracurricularActivity.extracurricularActivityName = name;
        extracurricularActivity.extracurricularActivityDate = new Date();
        return extracurricularActivity;
    }

    private void removeDefaultExtracurricularActivity() {
        defaultExtracurricularActivities.forEach(extracurricularActivityRepository::delete);
        defaultExtracurricularActivities = new ArrayList<>();
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
        var extracurricularActivityCount = 4;
        var groupsCount = 5;

        assertEquals(defaultExtracurricularActivities.size(), extracurricularActivityCount);
        assertEquals(defaultGroups.size(), groupsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (extracurricularActivityIndex, groupIndex) -> {
            var extracurricularActivityId = defaultExtracurricularActivities.get(extracurricularActivityIndex).id;
            var groupId = defaultGroups.get(groupIndex).id;
            var relation = new Relation(extracurricularActivityId, groupId);

            var optCreatedRelation = relationRepository.createRelation("ExtracurricularActivity", "groups", relation);
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
                .deleteRelation("ExtracurricularActivity", "groups", r.getId()));
        defaultGroups = new ArrayList<>();
    }
}

