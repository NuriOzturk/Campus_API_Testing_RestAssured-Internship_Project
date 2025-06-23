package campus;

import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class States extends ParentPage {

    @Test
    public void createState() {
        Map<String, Object> createState = new LinkedHashMap<>();
        createState.put("name", faker.address().state() + faker.number().digits(3));
        createState.put("shortName", faker.address().stateAbbr() + faker.number().digits(2));

        Map<String, String> countryId = new LinkedHashMap<>();
        countryId.put("id", ConfigReader.getProperty("countryID"));

        createState.put("country", countryId);
        String state_ID =
                given()
                        .spec(reqSpec)
                        .body(createState)
                        .when()
                        .post("/school-service/api/states")
                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().path("id");

        ConfigReader.updateProperty("stateID", state_ID);
    }

    @Test(dependsOnMethods = "createState")
    public void updateState() {
    }

    @Test(dependsOnMethods = "updateState")
    public void deleteState() {
    }
}
