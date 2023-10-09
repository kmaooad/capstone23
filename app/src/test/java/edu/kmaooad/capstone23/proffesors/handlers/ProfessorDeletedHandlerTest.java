package edu.kmaooad.capstone23.proffesors.handlers;
import edu.kmaooad.capstone23.proffesors.events.ProfessorDeleted;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorDeletedHandlerTest {

    @Test
    @DisplayName("Test valid ProfessorDeleted object")
    void testValidProfessorDeleted() {
        ObjectId professorId = new ObjectId();
        boolean professorExisted = true;
        ProfessorDeleted professorDeleted = new ProfessorDeleted(professorId, professorExisted);

        assertNotNull(professorDeleted.getProfessorId());

        assertTrue(professorDeleted.isProfessorExisted());
    }

    @Test
    @DisplayName("Test invalid ProfessorDeleted object with professor not existed")
    void testInvalidProfessorDeletedNotExisted() {
        ObjectId professorId = new ObjectId();
        boolean professorExisted = false;
        ProfessorDeleted professorDeleted = new ProfessorDeleted(professorId, professorExisted);

        assertNotNull(professorDeleted.getProfessorId());

        assertFalse(professorDeleted.isProfessorExisted());
    }

}
