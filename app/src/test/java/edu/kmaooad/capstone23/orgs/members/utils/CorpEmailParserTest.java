package edu.kmaooad.capstone23.orgs.members.utils;

import edu.kmaooad.capstone23.members.utils.CorpEmailParser;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CorpEmailParserTest {
    @Inject
    CorpEmailParser corpEmailParser;

    @Test
    void testIfNullIsPassed() {
        Assertions.assertNull(corpEmailParser.getCorpEmailDomain(null));
    }

    @Test
    void testCorrectWork() {
        Assertions.assertEquals("gmail.com", corpEmailParser.getCorpEmailDomain("me@gmail.com"));
    }

    @Test
    void testBadEmail() {
        Assertions.assertNull(corpEmailParser.getCorpEmailDomain("megmail.com"));
    }
}
