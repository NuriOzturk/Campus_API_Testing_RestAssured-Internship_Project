package campus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ConfigReader;
import utilities.ParentPage;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StudentGroup extends ParentPage {
    @Test
    public void createExams() {
        Map<String, Object> createExams = new HashMap<>();
        createExams.put("id", null);
        createExams.put("name", faker.science().element());
        createExams.put("type", "EXAM");
        createExams.put("school", ConfigReader.getProperty("schoolID"));

        Map<String, String> gradeLevel_id = new HashMap<>();
        gradeLevel_id.put("id", ConfigReader.getProperty("gradeLevelId"));
        createExams.put("gradeLevel", gradeLevel_id);
        createExams.put("academicPeriod", ConfigReader.getProperty("academicPeriod"));

        String examId = given()
                .spec(reqSpec)
                .body(createExams)
                .when()
                .post("/school-service/api/exams")
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract()
                .path("id");
        ConfigReader.updateProperty("examID", examId);
    }

    @Test(dependsOnMethods = "createExams")
    public void getStudent() {
        String student1 = "";
        String student2 = "";
        String student3 = "";
        String student4 = "";

        Response response = given()
                .spec(reqSpec)
                .when()
                .get("/school-service/api/incident/school/" + ConfigReader.getProperty("schoolID") + "/class/" + ConfigReader.getProperty("classID") + "/student-points");

        response
                .then()
                .statusCode(200)
                .log()
                .body();
        JsonPath jsonPath = response.jsonPath();

        student1 = jsonPath.getString("studentPoints[0].id");
        student2 = jsonPath.getString("studentPoints[1].id");
        student3 = jsonPath.getString("studentPoints[2].id");
        student4 = jsonPath.getString("studentPoints[3].id");

        ConfigReader.updateProperty("student1", student1);
        ConfigReader.updateProperty("student2", student2);
        ConfigReader.updateProperty("student3", student3);
        ConfigReader.updateProperty("student4", student4);
    }

    @Test(dependsOnMethods = "getStudent")
    public void createStudentGroup() {
        Map<String, Object> studentGroup = new HashMap<>();
        studentGroup.put("id", null);
        studentGroup.put("schoolId", ConfigReader.getProperty("schoolID"));
        studentGroup.put("name", faker.job().title());
        studentGroup.put("description", faker.lorem().paragraph());
        studentGroup.put("active", true);
        studentGroup.put("publicGroup", true);
        studentGroup.put("showToStudent", true);

        String groupID = given()
                .spec(reqSpec)
                .body(studentGroup)
                .when()
                .post("/school-service/api/student-group")
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract()
                .path("id");
        ConfigReader.updateProperty("groupID", groupID);
    }

    @Test(dependsOnMethods = "createStudentGroup")
    public void updateStudentGroup() {
        Map<String, Object> studentGroup = new HashMap<>();
        studentGroup.put("id", ConfigReader.getProperty("groupID"));
        studentGroup.put("schoolId", ConfigReader.getProperty("schoolID"));
        studentGroup.put("name", faker.job().title() + " Updated");
        studentGroup.put("description", faker.lorem().paragraph() + " Updated");
        studentGroup.put("active", true);
        studentGroup.put("publicGroup", true);
        studentGroup.put("showToStudent", true);

        given()
                .spec(reqSpec)
                .body(studentGroup)
                .when()
                .put("/school-service/api/student-group")
                .then()
                .statusCode(200)
                .log()
                .body();
    }

    @Test(dependsOnMethods = "updateStudentGroup")
    public void createStudent() {
        String student5 = given()
                .spec(reqSpec)
                .when()
                .get("/school-service/api/student-group/" + ConfigReader.getProperty("groupID"))
                .then()
                .statusCode(200)
                .log()
                .body()
                .extract()
                .path("id");
        ConfigReader.updateProperty("student5", student5);

    }

    @Test(dependsOnMethods = "createStudent")
    public void addStudentGroup() {
        List<String> students = new ArrayList<>();
        students.add(ConfigReader.getProperty("student1"));
        students.add(ConfigReader.getProperty("student2"));
        students.add(ConfigReader.getProperty("student3"));
        students.add(ConfigReader.getProperty("student4"));

        given()
                .spec(reqSpec)
                .body(students)
                .when()
                .post("/school-service/api/student-group/" + ConfigReader.getProperty("groupID") + "/add-students?page=0&size=10")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = "addStudentGroup")
    public void deleteStudent() {
        List<String>students=new ArrayList<>();
        students.add(ConfigReader.getProperty("student1"));

        given()
                .spec(reqSpec)
                .body(students)

                .when()
                .post("/school-service/api/student-group/" + ConfigReader.getProperty("groupID") + "/remove-students?page=0&size=10")

                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = "deleteStudent")
    public void deleteStudentGroup() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/student-group/" + ConfigReader.getProperty("groupID"))
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = "deleteStudentGroup")
    public void deleteNegativeStudentGroup() {
        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/student-group/" + ConfigReader.getProperty("groupID"))
                .then()
                .log().body()
                .statusCode(400);
    }
}
