package edu.kmaooad.capstone23.cvs.handlers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AddSkillToCVHandlerTest {

    private static Stream<Arguments> validAddSkillArgs() {
        return Stream.of();
    }

    private static Stream<Arguments> invalidAddSkillArgs() {
        return Stream.of();
    }

    @Test
    @DisplayName("Add skill handler test: valid input")
    void simpleValidHandleTest() {
    }



}