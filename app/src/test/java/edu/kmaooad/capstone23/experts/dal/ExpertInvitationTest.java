package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ExpertInvitationTest {

    @Inject
    ExpertInvitationRepository expertInvitationRepository;

    @Test
    public void testExpertInvitationPersistence() {
        var entity = new ExpertInvitation();
        entity.createdAt = LocalDateTime.now();
        entity.email = "expert@example.org";
        expertInvitationRepository.persist(entity);

        assertNotNull(entity.id);
    }
}