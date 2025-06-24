package campus;

import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class Incident extends ParentPage {

    @Test
    public void getIncident() {
        Map<String, Object> getIncident = new LinkedHashMap<>();
        getIncident.put("tenantId", ConfigReader.getProperty("tenantId"));

        given()
                .spec(reqSpec)
                .body(getIncident)

                .when()
                .post("/school-service/api/incident-type/search")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "getIncident")
    public void addIncident() {
        Map<String, Object> addIncident = new LinkedHashMap<>();
        addIncident.put("id", null);
        addIncident.put("name", faker.name().fullName());
        addIncident.put("active", true);
        addIncident.put("tenantId", ConfigReader.getProperty("tenantId"));
        addIncident.put("minPoint", 10);
        addIncident.put("maxPoint", 20);
        addIncident.put("academicBased", false);
        addIncident.put("permissions", new String[]{"ROLE_USER"});
        addIncident.put("notifyWithEmail", false);
        addIncident.put("notifyWithMessage", false);

        String incidentID =
                given()
                        .spec(reqSpec)
                        .body(addIncident)

                        .when()
                        .post("/school-service/api/incident-type")

                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().path("id");
        ConfigReader.updateProperty("incidentID", incidentID);
    }

    @Test(dependsOnMethods = "addIncident")
    public void updateIncident() {
        Map<String, Object> updateIncident = new LinkedHashMap<>();
        updateIncident.put("id", ConfigReader.getProperty("incidentID"));
        updateIncident.put("name", faker.name().fullName());
        updateIncident.put("active", true);
        updateIncident.put("tenantId", ConfigReader.getProperty("tenantId"));
        updateIncident.put("minPoint", 15);
        updateIncident.put("maxPoint", 20);
        updateIncident.put("academicBased", false);
        updateIncident.put("permissions", new String[]{"ROLE_USER"});
        updateIncident.put("notifyWithEmail", false);
        updateIncident.put("notifyWithMessage", false);

                given()
                        .spec(reqSpec)
                        .body(updateIncident)

                        .when()
                        .put("/school-service/api/incident-type")

                        .then()
                        .statusCode(200)
                        .log().body();
    }

    @Test(dependsOnMethods = "updateIncident")
    public void deleteIncident() {
        given()
                .spec(reqSpec)
                .pathParam("id", ConfigReader.getProperty("incidentID"))

                .when()
                .delete("/school-service/api/incident-type/{id}")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "deleteIncident")
    public void deleteNegativeIncident() {
        try {
            given()
                    .spec(reqSpec)
                    .pathParam("id", ConfigReader.getProperty("incidentID"))

                    .when()
                    .delete("/school-service/api/incident-type/{id}")

                    .then()
                    .log().body()
                    .statusCode(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}