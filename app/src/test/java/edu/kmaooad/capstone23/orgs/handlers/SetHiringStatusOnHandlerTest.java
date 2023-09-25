package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatusOn;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SetHiringStatusOnHandlerTest {

    @Inject
    SetHiringStatusOnHandler handler;

    @Inject
    OrgsRepository orgsRepository;
    private String orgId;

    @BeforeEach
    void setUp() {
        orgsRepository.deleteAll();
        Org org = new Org();

        org.name = "Initial Org";
        org.description = "Initial Org Description";
        org.isActive = true;
        org.industry = "Initial Organization Industry";
        org.website = "Initial Organization Website";
        org.emailDomain = "Initial Organization Domain";
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

}
