package edu.kmaooad.capstone23.experts.controllers;

import edu.kmaooad.capstone23.experts.dal.Expert;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitation;
import edu.kmaooad.capstone23.experts.dal.ExpertInvitationRepository;
import edu.kmaooad.capstone23.experts.dal.ExpertsRepository;
import edu.kmaooad.capstone23.experts.service.ExpertService;
import edu.kmaooad.capstone23.orgs.dal.Org;
import edu.kmaooad.capstone23.orgs.dal.OrgsRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class InviteExpertTest {
    @Inject
    OrgsRepository orgsRepository;

    @Inject
    ExpertInvitationRepository expertInvitationRepository;
    @Inject
    ExpertService expertService;
    @Inject
    ExpertsRepository expertsRepository;

    @Test
    public void createsExpertOnInvitationAccept() throws URISyntaxException {
        Org org = new Org();
        org.name = "My org";
        orgsRepository.persist(org);
        ExpertInvitation invitation = new ExpertInvitation();
        invitation.expertName = "Oleg Klepatskyi";
        invitation.org = org;
        invitation.email = "a@mail.com";
        expertInvitationRepository.persist(invitation);
        URI uri = new URI(expertService.createInvitationLink(invitation.id));
        String pathAndQuery = uri.getPath() + (uri.getQuery() != null ? "?" + uri.getQuery() : "");

        given()
                .contentType("application/json")
                .when()
                .get(pathAndQuery)
                .then()
                .statusCode(200);

        Expert createdExpert = expertsRepository.findByName("Oleg Klepatskyi");
        assertNotNull(createdExpert);
        assertEquals(org, createdExpert.org);
    }

    @AfterEach
    public void teardown() {
        orgsRepository.deleteAll();
        expertInvitationRepository.deleteAll();
        expertsRepository.deleteAll();
    }
}
