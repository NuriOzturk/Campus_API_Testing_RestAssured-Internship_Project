package campus;

import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class Grading extends ParentPage {

    @Test
    public void getGrading() {
        given().spec(reqSpec)

                .when()
                .get("/school-service/api/grading-schemes/tenant/" + ConfigReader.getProperty("tenantId") + "/search")

                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "getGrading")
    public void createScheme() {
        Map<String, Object> createGrading = new LinkedHashMap<>();
        createGrading.put("id", null);
        createGrading.put("name", faker.name().fullName());
        createGrading.put("tenantId",ConfigReader.getProperty("tenantId"));
        createGrading.put("active", "true");
        createGrading.put("type", "POINT");
        createGrading.put("enablePoint", "false");
        createGrading.put("schoolId", ConfigReader.getProperty("schoolID"));

        String gradingID = given().spec(reqSpec)
                .body(createGrading)

                .when()
                .post("/school-service/api/grading-schemes")

                .then()
                .statusCode(201)

                .log().body()
                .extract().path("id");
        ConfigReader.updateProperty("gradingID", gradingID);
    }

    @Test(dependsOnMethods = "createScheme")
    public void updateScheme() {
        Map<String, Object> createGrading = new LinkedHashMap<>();
        createGrading.put("id", ConfigReader.getProperty("gradingID"));
        createGrading.put("name", faker.name().fullName());
        createGrading.put("tenantId",ConfigReader.getProperty("tenantId"));
        createGrading.put("active", "false");
        createGrading.put("schoolId", ConfigReader.getProperty("schoolID"));
        createGrading.put("type", "POINT");
        createGrading.put("enablePoint", "false");

        given()
                .spec(reqSpec)
                .body(createGrading)

                .when()
                .put("/school-service/api/grading-schemes")

                .then()
                .statusCode(200)

                .log().body();
    }

    @Test(dependsOnMethods = "updateScheme")
    public void deleteGradingScheme() {
        given()
                .spec(reqSpec)

                .when()
                .delete("/school-service/api/grading-schemes/" + ConfigReader.getProperty("gradingID"))

                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "deleteGradingScheme")
    public void deleteNegativeGradingScheme() {
        try {
            given()
                    .spec(reqSpec)

                    .when()
                    .delete("/school-service/api/grading-schemes/" + ConfigReader.getProperty("gradingID"))

                    .then()
                    .statusCode(400);
        } catch (Exception e) {
            System.out.println("Error message: " + e);
        }
    }
}