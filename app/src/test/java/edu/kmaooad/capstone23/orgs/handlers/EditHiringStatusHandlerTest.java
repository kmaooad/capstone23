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
        CommandHandler<SetHiringStatus, HiringStatusChanged> handler;

        @Test
        void testSuccessfulHandling() {
//            Works if there is org with id <64ff7fabc64e527ecae3f896> in database
            SetHiringStatus command = new SetHiringStatus();
            command.setOrgID("64ff7fabc64e527ecae3f896");
            command.setHiringStatus(HiringStatus.HIRING);

            Result<HiringStatusChanged> result = handler.handle(command);

            Assertions.assertTrue(result.isSuccess());
            Assertions.assertNotNull(result.getValue());
            Assertions.assertFalse(result.getValue().getOrgId().isEmpty());
        }

    }
