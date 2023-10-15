package edu.kmaooad.capstone23.search.extracurricularActivities.by.org.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivity;
import edu.kmaooad.capstone23.activities.dal.ExtracurricularActivityRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.relations.dal.Relation;
import edu.kmaooad.capstone23.relations.dal.RelationRepository;
import edu.kmaooad.capstone23.search.QueryByIdCommand;
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
class QueryExtracurricularActivitiesByOrgControllerTest {
    @Inject
    ExtracurricularActivityRepository extracurricularActivityRepository;

    @Inject
    OrgsRepository orgsRepository;

    @Inject
    RelationRepository relationRepository;

    private List<ExtracurricularActivity> defaultExtracurricularActivities;
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
                .post("/search/extracurricularActivity/by/org")
                .asString();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode queriedExtracurricularActivitiesNode = rootNode.get("queriedExtracurricularActivities");
            List<ExtracurricularActivity> foundExtracurricularActivities = new ArrayList<>();

            for (JsonNode activityNode : queriedExtracurricularActivitiesNode) {
                ExtracurricularActivity activity = objectMapper.treeToValue(activityNode, ExtracurricularActivity.class);
                foundExtracurricularActivities.add(activity);
            }

            System.out.println("FOUND EXTRACURRICULAR ACTIVITIES NAMES:");
            for (var activity : foundExtracurricularActivities) {
                System.out.println(activity.extracurricularActivityName);
            }

            var manuallyFoundExtracurricularActivities = manualSearch(orgToFindIndex);

            assertTrue(areEqualSets(foundExtracurricularActivities, manuallyFoundExtracurricularActivities));
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

    private List<ExtracurricularActivity> manualSearch(int orgIndex) {
        var org = defaultOrgs.get(orgIndex);
        var orgId = org.id;

        return defaultRelations
                .stream()
                .filter(relation -> relation.secondObjectId.equals(orgId))
                .map(Relation::getFirstObjectId)
                .map(id -> {
                    for (var extracurricularActivity : defaultExtracurricularActivities) {
                        if (extracurricularActivity.id.equals(id))
                            return extracurricularActivity;
                    }
                    return null;
                })
                .toList();
    }

    private boolean isSubset(List<ExtracurricularActivity> subset, List<ExtracurricularActivity> set) {
        return subset
                .stream()
                .map(activity -> activity.extracurricularActivityName)
                .map(name -> {
                    for (var activity : set) {
                        if (activity.extracurricularActivityName.equals(name))
                            return true;
                    }
                    return false;
                })
                .reduce(true, Boolean::logicalAnd);
    }

    private boolean areEqualSets(List<ExtracurricularActivity> set1, List<ExtracurricularActivity> set2) {
        return isSubset(set1, set2) && isSubset(set2, set1);
    }

    @BeforeEach
    public void setUp() {
        addDefaultExtracurricularActivity();
        addDefaultOrgs();
        addDefaultRelations();
    }

    @AfterEach
    public void tearDown() {
        removeDefaultExtracurricularActivity();
        removeDefaultOrgs();
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

    private void addDefaultOrgs() {
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
        defaultOrgs.add(createOrg("org6", "Industry6", "www.org6.com",
                "Description for org6", "org6.com", false));

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
        var extracurricularActivityCount = 4;
        var orgsCount = 5;

        assertEquals(defaultExtracurricularActivities.size(), extracurricularActivityCount);
        assertEquals(defaultOrgs.size(), orgsCount);

        BiFunction<Integer, Integer, Relation> addRelation = (extracurricularActivityIndex, orgIndex) -> {
            var extracurricularActivityId = defaultExtracurricularActivities.get(extracurricularActivityIndex).id;
            var orgId = defaultOrgs.get(orgIndex).id;
            var relation = new Relation(extracurricularActivityId, orgId);

            var optCreatedRelation = relationRepository.createRelation("ExtracurricularActivity", "orgs", relation);
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
                .deleteRelation("ExtracurricularActivity", "orgs", r.getId()));
        defaultOrgs = new ArrayList<>();
    }
}
