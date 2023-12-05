package edu.kmaooad.capstone23.search.extracurricularActivities.by.org.handlers;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class QueryExtracurricularActivitiesByOrgHandlerTest {

    @Inject
    QueryExtracurricularActivitiesByOrgHandler handler;

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
        var command = new QueryByIdCommand();
        command.id = idOfOrgToFindBy;

        var result = handler.handle(command);
        assertTrue(result.isSuccess());

        var event = result.getValue();
        var foundExtracurricularActivities = event.queriedExtracurricularActivities();
        var manuallyFoundExtracurricularActivities = manualSearch(orgToFindIndex);

        assertTrue(areEqualSets(foundExtracurricularActivities, manuallyFoundExtracurricularActivities));
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
                .map(extracurricularActivity -> extracurricularActivity.extracurricularActivityName)
                .map(name -> {
                    for (var extracurricularActivity : set) {
                        if (extracurricularActivity.extracurricularActivityName.equals(name))
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
            var relation = new Relation(orgId, extracurricularActivityId);

            var optCreatedRelation = relationRepository.createRelation("orgs", "extracurricularActivities", relation);
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
                .deleteRelation("orgs", "extracurricularActivities", r.getId()));
        defaultOrgs = new ArrayList<>();
    }
}
