package campus;

import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class StudentGroup extends ParentPage {
    @Test
    public void createExams() {
        Map<String, Object> createExams = new LinkedHashMap<>();
        createExams.put("name", faker.science().element());
        createExams.put("type", "EXAM");
        createExams.put("school", ConfigReader.getProperty("schoolID"));

        Map<String, String> gradeLevel_id = new LinkedHashMap<>();
        gradeLevel_id.put("gradeLevel", ConfigReader.getProperty("gradeLevelId"));
        createExams.put("gradeLevel", gradeLevel_id);

        Map<String, String> academicPeriodId= new LinkedHashMap<>();
        academicPeriodId.put("academicPeriod", ConfigReader.getProperty("academicPeriod"));
        createExams.put("academicPeriod", academicPeriodId);

        String examId =
                given()
                        .spec(reqSpec)
                        .body(createExams)
                        .when()
                        .post("/school-service/api/exams")
                        .then()
                       // .statusCode(201)
                        .log().body()
                        .extract().path("id");

      //  ConfigReader.updateProperty("examID", examId);
    }

    @Test
    public void getStudent() {

    }

    @Test(dependsOnMethods = "getStudent")
    public void createStudentGroup() {


    }

    @Test(dependsOnMethods = "createStudentGroup")
    public void updateStudentGroup() {

    }

    @Test()
    public void createStudent() {

    }

    @Test(dependsOnMethods = "createStudent")
    public void addStudentGroup() {

    }

    @Test(dependsOnMethods = "addStudentGroup")
    public void deleteStudent() {

    }

    @Test
    public void deleteStudentGroup() {

    }

    @Test(dependsOnMethods = "deleteStudentGroup")
    public void deleteNegativeStudentGroup() {

    }
}
