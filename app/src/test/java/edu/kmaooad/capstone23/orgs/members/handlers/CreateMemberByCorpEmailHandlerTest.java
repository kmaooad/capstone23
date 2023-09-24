package edu.kmaooad.capstone23.orgs.members.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.members.commands.CreateMemberByCorpEmail;
import edu.kmaooad.capstone23.members.events.BasicMemberCreated;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.members.TestWithDbClearance;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateMemberByCorpEmailHandlerTest extends TestWithDbClearance {
    @Inject
    CommandHandler<CreateMemberByCorpEmail, BasicMemberCreated> handler;

    private String orgEmailDomain;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        membersRepository.deleteAll();

        Org org = new Org();
        org.name = "Ubi";
        org.industry = "Entertainment";
        org.website = "https://www.ubisoft.com";
        org.emailDomain = "ubisoft.com";
        orgsRepository.insert(org);

        orgEmailDomain = org.emailDomain;
    }

    @Test
    @DisplayName("Create member: Basic handling")
    void testSuccessfulHandling() {
        CreateMemberByCorpEmail command = new CreateMemberByCorpEmail();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setCorpEmail("newCorpEmail@" + orgEmailDomain);
        command.setIsExpert("false");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());
        Assertions.assertFalse(result.getValue().getMemberId().isEmpty());
    }

    @Test
    @DisplayName("Create member: Wrong corp email handling")
    void testCorpEmailValidation() {
        CreateMemberByCorpEmail command = new CreateMemberByCorpEmail();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setCorpEmail("wrongCorpEmail@" + orgEmailDomain + "HELLO");
        command.setIsExpert("false");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }

    @Test
    @DisplayName("Create member: Email uniqueness validation handling")
    void testEmailUniquenessValidation() {
        CreateMemberByCorpEmail command = new CreateMemberByCorpEmail();
        command.setFirstName("firstName");
        command.setLastName("lastName");
        command.setCorpEmail("newCorpEmail@" + orgEmailDomain);

        handler.handle(command);

        command = new CreateMemberByCorpEmail();
        command.setFirstName("firstName");
        command.setLastName("lastName");

        Result<BasicMemberCreated> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(ErrorCode.VALIDATION_FAILED, result.getErrorCode());
    }
}
