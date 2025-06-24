package campus;

import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Cities extends ParentPage {

    @Test
    public void createCities() {
        Map<String, Object> createCities = new LinkedHashMap<>();
        createCities.put("name", faker.address().city());

        Map<String, String> countryId = new LinkedHashMap<>();
        countryId.put("id", ConfigReader.getProperty("countryID"));
        createCities.put("country", countryId);

        Map<String, String> stateId = new LinkedHashMap<>();
        stateId.put("state", ConfigReader.getProperty("statesID"));
        createCities.put("state", stateId);

        String citiesId =
                given()
                        .spec(reqSpec)
                        .body(createCities)
                        .when()
                        .post("/school-service/api/cities")
                        .then()
                        .statusCode(201)
                        .assertThat().time(lessThan(1000L))
                        .log().body()
                        .extract().path("id");
        ConfigReader.updateProperty("citiesID", citiesId);
    }

    @Test(dependsOnMethods = "createCities")
    public void updateCities() {
        Map<String, Object> updateCities = new LinkedHashMap<>();
        updateCities.put("id", ConfigReader.getProperty("citiesID"));
        updateCities.put("name", faker.address().city());

        Map<String, String> countryId = new LinkedHashMap<>();
        countryId.put("id", ConfigReader.getProperty("countryID"));
        updateCities.put("country", countryId);

        Map<String, String> stateId = new LinkedHashMap<>();
        stateId.put("state", ConfigReader.getProperty("statesID"));
        updateCities.put("state", stateId);

        given()
                .spec(reqSpec)
                .body(updateCities)
                .when()
                .put("/school-service/api/cities")
                .then()
                .statusCode(200)
                .log().body()
                .assertThat().time(lessThan(1000L));
    }

    @Test(dependsOnMethods = "updateCities")
    public void deleteCities() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/cities/" + ConfigReader.getProperty("citiesID"))
                .then()
                .statusCode(200)
                .assertThat().time(lessThan(1000L));
    }
}