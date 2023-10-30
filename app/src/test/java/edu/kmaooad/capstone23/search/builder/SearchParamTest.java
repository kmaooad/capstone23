package edu.kmaooad.capstone23.search.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;

public abstract class SearchParamTest {
    protected SearchBuilder builder;

    @BeforeEach
    void setup() {
        builder = new SearchBuilder();
    }

    void testResult(String expected, Map<String, Object> values) {
        var result = builder.build();
        Assertions.assertEquals(expected, result.a);
        Assertions.assertEquals(values, result.b.map());
    }
}
