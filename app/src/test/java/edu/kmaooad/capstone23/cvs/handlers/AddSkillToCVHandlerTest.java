package edu.kmaooad.capstone23.cvs.handlers;

import edu.kmaooad.capstone23.cvs.dal.CVRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AddSkillToCVHandlerTest {

    @Inject
    CVRepository cvRepository;

    @Test
    @DisplayName("Add skill handler test: valid cv")
    void simpleValidHandleTest() {

    }



}