package edu.kmaooad.capstone23.common;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ControllerTest<T> {
  protected final String endpointUrl;

  protected ControllerTest(String url) {
    this.endpointUrl = url;
  }

  protected void assertRequestSucceeds(T payload) {
    assertResponseStatusCode(payload, 200);
  }

  protected void assertRequestFails(T payload) {
    assertResponseStatusCode(payload, 500);
  }

  private void assertResponseStatusCode(T payload, int statusCode) {
    RestAssured.given()
        .contentType("application/json")
        .body(mapPayloadToStringifiedJson(payload))
        .when()
        .post(endpointUrl)
        .then()
        .statusCode(statusCode);
  }

  protected String mapPayloadToStringifiedJson(T entity) {
    try {
      ObjectMapper mapper = new ObjectMapper();

      return mapper.writeValueAsString(entity);
    } catch (JsonProcessingException exception) {
      Assertions.fail("Command payload parsing failed, force-fail test", exception);

      return "";
    }
  }
}
