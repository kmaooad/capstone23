package edu.kmaooad.capstone23.orgs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.orgs.commands.CreateOrg;
import edu.kmaooad.capstone23.orgs.commands.SetHiringStatus;
import edu.kmaooad.capstone23.orgs.dal.HiringStatus;
import edu.kmaooad.capstone23.orgs.events.HiringStatusChanged;
import edu.kmaooad.capstone23.orgs.events.OrgCreated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class EditHiringStatusHandlerTest {

    @Inject
    CommandHandler<SetHiringStatus, HiringStatusChanged> handlerStatus;
    @Inject
    CommandHandler<CreateOrg, OrgCreated> handlerOrg;

    @Test
    void testSuccessfulHandling() {
        CreateOrg commandOrg = new CreateOrg();
        commandOrg.setOrgName("TestOrg");
        Result<OrgCreated> resultOrg = handlerOrg.handle(commandOrg);

        SetHiringStatus commandStatus = new SetHiringStatus();
        commandStatus.setOrgID(resultOrg.getValue().getOrgId());
        commandStatus.setHiringStatus(HiringStatus.HIRING);

        Result<HiringStatusChanged> result = handlerStatus.handle(commandStatus);

        Assertions.assertTrue(result.isSuccess());
    }

}
