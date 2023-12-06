package edu.kmaooad.capstone23.search.builder;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@QuarkusTest
public class EqualsSearchParamTest extends SearchParamTest {
    @Test
    @DisplayName("Test EqualsSearchParam")
    void testEqualsSearchParam() {
        var param = new EqualsSearchParam("testName", "testValue");
        builder.and(param);
        testResult("{'testName': :testName}", Map.of("testName", "testValue"));
    }
}
