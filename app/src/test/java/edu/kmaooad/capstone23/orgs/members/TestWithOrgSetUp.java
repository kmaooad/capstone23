package edu.kmaooad.capstone23.orgs.members;

import edu.kmaooad.capstone23.common.Mocks;
import edu.kmaooad.capstone23.members.dal.MembersRepository;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.users.interfaces.UserRepository;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestWithOrgSetUp {

    protected ObjectId createdOrgId;

    @Inject
    protected MembersRepository membersRepository;

    @Inject
    protected OrgsRepository orgsRepository;

    @Inject
    protected UserRepository userRepository;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        membersRepository.deleteAll();
        userRepository.deleteAll();
        var org = new Org();
        org.name = "NaUKMA";
        orgsRepository.insert(org);
        createdOrgId = org.id;
    }

    protected String createMember() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("firstName", "firstName");
        jsonAsMap.put("lastName", "lastName");
        jsonAsMap.put("orgId", createdOrgId.toString());
        jsonAsMap.put("email", Mocks.mockValidEmail());
        return given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/create")
                .then()
                .statusCode(200)
                .extract()
                .path("memberId");
    }
}
