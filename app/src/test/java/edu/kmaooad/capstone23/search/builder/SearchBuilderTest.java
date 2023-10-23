package edu.kmaooad.capstone23.search.builder;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SearchBuilderTest {
    @Test
    @DisplayName("Test SearchBuilder empty")
    void testSearchBuilderEmpty() {
        SearchBuilder builder = new SearchBuilder();
        var result = builder.build();

        Assertions.assertEquals("{}", result.a);
        Assertions.assertTrue(result.b.map().isEmpty());
    }

    @Test
    @DisplayName("Test SearchBuilder 1 line")
    void testSearchBuilderOneLine() {
        SearchBuilder builder = new SearchBuilder();
        builder.and(new MockSearchParam("query1",null));
        var result = builder.build();

        Assertions.assertEquals("{query1}", result.a);
        Assertions.assertTrue(result.b.map().containsKey("query1"));
    }

    @Test
    @DisplayName("Test SearchBuilder multiple lines")
    void testSearchBuilderMultipleLine() {
        SearchBuilder builder = new SearchBuilder();
        Integer param2 = 5;
        builder.and(new MockSearchParam("query1",null));
        builder.and(new MockSearchParam("query2",param2));
        builder.and(new MockSearchParam("query3",null));
        var result = builder.build();

        Assertions.assertEquals("{query1, query2, query3}", result.a);

        var resultParam = result.b.map();
        Assertions.assertEquals(3, resultParam.size());
        Assertions.assertNull(resultParam.get("query1"));
        Assertions.assertSame(param2, resultParam.get("query2"));
        Assertions.assertNull(resultParam.get("query3"));
    }
}

class MockSearchParam extends SearchParam {
    MockSearchParam(String paramName, Object paramValue) {
        super(paramName, paramValue);
        query = paramName;
    }
}
