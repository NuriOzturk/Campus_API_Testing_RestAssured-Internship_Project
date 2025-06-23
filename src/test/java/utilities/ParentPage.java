package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ParentPage {
    public RequestSpecification reqSpec;
    public Faker faker=new Faker();

    @BeforeClass
    public void setUp() {
        baseURI = ConfigReader.getProperty("baseURI");

        Map<String, String> userCredentials = new HashMap<>();
        userCredentials.put("username",ConfigReader.getProperty("username"));
        userCredentials.put("password", ConfigReader.getProperty("password"));
        userCredentials.put("rememberMe", "true");

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(userCredentials)

                        .when()
                        .post("/auth/login");
        response.then().statusCode(200);

        String token = response.jsonPath().getString("access_token");


        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader( "Authorization", "Bearer " + token)
                .build();
        ConfigReader.updateProperty("token", token);
    }
}
