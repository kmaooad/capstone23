package edu.kmaooad.capstone23.common;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ControllerTest<PayloadType> {
  protected final String endpointUrl;

  protected ControllerTest(String url) {
    this.endpointUrl = url;
  }

  protected void assertRequestSucceeds(PayloadType payload) {
    assertResponseStatusCode(payload, 200);
  }

  protected void assertRequestFails(PayloadType payload) {
    assertResponseStatusCode(payload, 400);
  }

  private void assertResponseStatusCode(PayloadType payload, int statusCode) {
    RestAssured.given()
        .contentType("application/json")
        .body(mapPayloadToStringifiedJson(payload))
        .when()
        .post(endpointUrl)
        .then()
        .statusCode(statusCode);
  }

  protected String mapPayloadToStringifiedJson(PayloadType entity) {
    try {
      ObjectMapper mapper = new ObjectMapperSerializingToHexObjectId();

      return mapper.writeValueAsString(entity);
    } catch (JsonProcessingException exception) {
      Assertions.fail("Command payload parsing failed, force-fail test", exception);

      return "";
    }
  }
}
