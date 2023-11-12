package edu.kmaooad.capstone23.search.builder;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@QuarkusTest
public class PrefixSearchParamTest extends SearchParamTest {
    @Test
    @DisplayName("Test PrefixSearchParam")
    void testPrefixSearchParam() {
        var param = new PrefixSearchParam("testName", "testValue");
        builder.and(param);
        testResult("{'testName': /^testValue/}", Map.of());
    }
}
