package edu.kmaooad.capstone23.orgs.members.controllers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateMemberBasic;
import edu.kmaooad.capstone23.members.events.CreatedMemberBasic;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DeleteMemberControllerTest {
    @Inject
    CommandHandler<CreateMemberBasic, CreatedMemberBasic> createHandler;

    @Test
    @DisplayName("Delete member: delete existing")
    public void testBasicMemberDeletion() {
        CreateMemberBasic command = new CreateMemberBasic();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId("64f9db2b6cf30055cb6ddcbe");
        command.setEmail("email@email.com");
        Result<CreatedMemberBasic> result = createHandler.handle(command);

        given()
                .when()
                .delete("/members/".concat(result.getValue().getMemberId()))
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("Delete member: delete non-existent member")
    public void testMemberDeletionWhenNonExistentIdIsProvided() {
        CreateMemberBasic command = new CreateMemberBasic();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setOrgId("64f9db2b6cf30055cb6ddcbe");
        command.setEmail("email@email.com");
        Result<CreatedMemberBasic> result = createHandler.handle(command);

        given()
                .when()
                .delete("/members/".concat(result.getValue().getMemberId()).replace("a", "1"))
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("Delete member: delete with invalid id")
    public void testMemberDeletionWhenInvalidIdIsProvided() {
        given()
                .when()
                .delete("/members/".concat("tooShort"))
                .then()
                .statusCode(400);
    }
}