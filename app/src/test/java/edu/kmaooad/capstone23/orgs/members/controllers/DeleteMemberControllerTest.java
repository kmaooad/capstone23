package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Mocks;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateBasicMember;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.members.TestWithOrgSetUp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class DeleteMemberControllerTest extends TestWithOrgSetUp {
    @Inject
    CommandHandler<CreateBasicMember, BasicMemberCreated> createHandler;

    @Test
    @DisplayName("Delete member: delete existing")
    public void testBasicMemberDeletion() {
        CreateBasicMember command = new CreateBasicMember();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId(createdOrgId.toString());
        command.setEmail(Mocks.mockValidEmail());
        command.setIsExpert("false");
        Result<BasicMemberCreated> result = createHandler.handle(command);

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("memberId", result.getValue().getMemberId());

        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/delete")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Delete member: delete non-existent member")
    public void testMemberDeletionWhenNonExistentIdIsProvided() {
        var newObjectId = new ObjectId();
        while (membersRepository.findByIdOptional(newObjectId).isPresent())
            newObjectId = new ObjectId();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("memberId", newObjectId.toString());

        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/delete")
                .then()
                .statusCode(200)
                .assertThat()
                .body("success", equalTo(false));
    }

    @Test
    @DisplayName("Delete member: delete with invalid id")
    public void testMemberDeletionWhenInvalidIdIsProvided() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("memberId", "tooShort");
        given().contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/members/delete")
                .then()
                .statusCode(400);
    }
}
