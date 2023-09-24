package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.common.CommandHandler;
import edu.kmaooad.capstone23.common.Result;
import edu.kmaooad.capstone23.competences.commands.CreateSkill;
import edu.kmaooad.capstone23.competences.dal.SkillsRepository;
import edu.kmaooad.capstone23.competences.events.SkillCreated;
import edu.kmaooad.capstone23.cvs.commands.AddSkillToCV;
import edu.kmaooad.capstone23.cvs.commands.CreateCV;
import edu.kmaooad.capstone23.cvs.commands.RemoveSkillFromCV;
import edu.kmaooad.capstone23.cvs.commands.UpdateCV;
import edu.kmaooad.capstone23.cvs.dal.CV;
import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import edu.kmaooad.capstone23.cvs.events.CVCreated;
import edu.kmaooad.capstone23.cvs.events.CVUpdated;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ejb.Init;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RemoveSkillToCVHandlerTest {


    @Inject
    CommandHandler<CreateCV, CVCreated> createCVHandler;

    @Inject
    CommandHandler<CreateSkill, SkillCreated> createSkillHandler;
    @Inject
    CommandHandler<AddSkillToCV, CVUpdated> addSkillHandler;

    @Inject
    CommandHandler<RemoveSkillFromCV, CVUpdated> removeSkillHandler;

    private ObjectId getCreateCvId() {
        CreateCV createCV = new CreateCV();


        createCV.setDateTimeCreated(LocalDateTime.now());
        createCV.setTextInfo("some info about a student");
        createCV.setStatus(CV.Status.OPEN);
        createCV.setVisibility(CV.Visibility.VISIBLE);


        Result<CVCreated> result = createCVHandler.handle(createCV);
        return result.getValue().getCVId();
    }

    private ObjectId getSkillId() {
        CreateSkill createSkill = new CreateSkill();
        createSkill.setSkillName("Embedded C");

        Result<SkillCreated> result = createSkillHandler.handle(createSkill);
        return result.getValue().getSkill();
    }

    @Init
    void init() {

    }

    @Test
    @DisplayName("Remove skill handler test: valid cv")
    void simpleValidHandleTest() {
        AddSkillToCV command = new AddSkillToCV();
        ObjectId skillId = getSkillId();
        command.setCvId(getCreateCvId());
        command.setSkillId(skillId);

        Result<CVUpdated> result = addSkillHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        RemoveSkillFromCV removeCommand = new RemoveSkillFromCV();
        removeCommand.setCvId(result.getValue().getCVId());
        removeCommand.setSkillId(skillId);

        Result<CVUpdated> result1 = removeSkillHandler.handle(removeCommand);

        Assertions.assertTrue(result1.isSuccess());
        Assertions.assertNotNull(result1.getValue());
    }

    @Test
    @DisplayName("Remove skill handler test: invalid cv")
    void simpleInvalidHandleTest() {
        AddSkillToCV command = new AddSkillToCV();
        ObjectId skillId = getSkillId();
        command.setCvId(getCreateCvId());
        command.setSkillId(skillId);

        Result<CVUpdated> result = addSkillHandler.handle(command);

        Assertions.assertTrue(result.isSuccess());
        Assertions.assertNotNull(result.getValue());

        RemoveSkillFromCV removeCommand = new RemoveSkillFromCV();
        removeCommand.setCvId(new ObjectId("aaaaaaaaaaaaaaaaaaaaaaaa"));
        removeCommand.setSkillId(skillId);

        Result<CVUpdated> result1 = removeSkillHandler.handle(removeCommand);

        Assertions.assertFalse(result1.isSuccess());
        Assertions.assertNull(result1.getValue());
    }



}