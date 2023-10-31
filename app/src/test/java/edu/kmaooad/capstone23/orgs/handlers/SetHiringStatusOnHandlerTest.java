package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.ban.commands.BanEntity;
import edu.kmaooad.capstone23.ban.dal.BannedEntityType;
import edu.kmaooad.capstone23.ban.events.EntityBanned;
import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.ErrorCode;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import edu.kmaooad.capstone23.orgs.events.HiringStatusSettedOn;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SetHiringStatusOnHandlerTest {

    @Inject
    SetHiringStatusOnHandler handler;

    @Inject
    CommandHandler<BanEntity, EntityBanned> banHandler;

    @Inject
    OrgsRepository orgsRepository;
    private String orgId;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        Org org = new Org();
        org.name = "NaUKMA";
        org.industry = "Education";
        org.website = "https://www.ukma.edu.ua/eng/";
        orgsRepository.insert(org);

        orgId = org.id.toString();
    }


    @Test
    @DisplayName("Set hiring status on")
    void setHiringStatusOn() {
        SetHiringStatusOn command = new SetHiringStatusOn();

        command.setOrgId(orgId);

        Result result = handler.handle(command);

        Assertions.assertEquals(result.getErrorCode(), null);
        Assertions.assertTrue(result.isSuccess());

        Org org = orgsRepository.findById(orgId);

        Assertions.assertEquals(org.hiringStatus, "We are hiring");
    }

    @Test
    @DisplayName("Set hiring status on with wrong id")
    void setHiringStatusOnWithWrongId() {
        SetHiringStatusOn command = new SetHiringStatusOn();
        command.setOrgId("64fbb243275c1111167b87a3");

        Result result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    @DisplayName("Set hiring on test: Org is banned")
    void setHiringStatusWithBannedTest() {
        var banRequest = new BanEntity();
        banRequest.setEntityId(new ObjectId(orgId));
        banRequest.setEntityType(BannedEntityType.Organization.name());
        banRequest.setReason("Hello there");

        var banResult = banHandler.handle(banRequest);

        Assertions.assertTrue(banResult.isSuccess());
        Assertions.assertNotNull(banResult.getValue());

        SetHiringStatusOn command = new SetHiringStatusOn();

        command.setOrgId(orgId);

        Result<HiringStatusSettedOn> result = handler.handle(command);

        Assertions.assertFalse(result.isSuccess());
        Assertions.assertEquals(result.getErrorCode(), ErrorCode.VALIDATION_FAILED);
        Assertions.assertEquals(result.getMessage(), "Org is banned");
    }
}
