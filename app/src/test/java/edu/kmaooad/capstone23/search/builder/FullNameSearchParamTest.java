package edu.kmaooad.capstone23.search.builder;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@QuarkusTest
public class FullNameSearchParamTest extends SearchParamTest {
    @Test
    @DisplayName("Test FullNameSearchParam")
    void testFullNameSearchParam() {
        var param = new FullNameSearchParam("testValue");
        builder.and(param);
        testResult("""
                {$or: [
                    { "firstName": /^testValue/ },
                    { "middleName": /^testValue/ },
                    { "lastName": /^testValue/ },
                ]}""", Map.of());
    }
}
