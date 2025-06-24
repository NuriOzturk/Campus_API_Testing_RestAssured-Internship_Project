package campus;

import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class Country extends ParentPage {

    @Test
    public void createCountry() {
        Map<String, String> addCountry = new HashMap<>();
        addCountry.put("name", faker.country().name() + faker.number().digits(3));
        addCountry.put("code", faker.country().countryCode3());
        addCountry.put("hasState", "true");

        String country_ID =
                given()
                        .spec(reqSpec)
                        .body(addCountry)
                        .when()
                        .post("/school-service/api/countries")
                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().path("id");
        ConfigReader.updateProperty("countryID", country_ID);
    }
}