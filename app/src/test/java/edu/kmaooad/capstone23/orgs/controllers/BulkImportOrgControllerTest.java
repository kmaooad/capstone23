package edu.kmaooad.capstone23.orgs.controllers;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@QuarkusTest
public class BulkImportOrgControllerTest {

    @Test
    @DisplayName("Import Org: Basic")
    public void testBasicImportOrgs() {
        LinkedList<Map<String, Object>> listOfOrgs = new LinkedList<>();
        
        {
            Map<String, Object> org1 = new HashMap<>();
            org1.put("orgName", "NaUKMA");
            org1.put("industry", "Education");
            org1.put("website", "https://www.ukma.edu.ua/eng/");
            listOfOrgs.push(org1);
        }
        
        {
            Map<String, Object> org2 = new HashMap<>();
            org2.put("orgName", "KPI");
            org2.put("industry", "Education");
            org2.put("website", "https://kpi.com/uk/");
            
            listOfOrgs.push(org2);
        }

        {
            Map<String, Object> org3 = new HashMap<>();
            org3.put("orgName", "E Corp");
            org3.put("industry", "Technology");
            org3.put("website", "https://e.corp.com");
            
            listOfOrgs.push(org3);
        }
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("orgs", listOfOrgs);

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/orgs/import")
                .then()
                .statusCode(200);
    }
}
