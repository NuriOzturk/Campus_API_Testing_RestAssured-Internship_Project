package campus;

import utilities.ParentPage;
import org.testng.annotations.*;

public class Grading extends ParentPage {

    @Test
    public void getGrading() {
    }

    @Test(dependsOnMethods = "getGrading")
    public void createScheme() {
    }

    @Test(dependsOnMethods = "createScheme")
    public void updateScheme() {
    }

    @Test(dependsOnMethods = "updateScheme")
    public void deleteGradingScheme() {
    }

    @Test(dependsOnMethods = "deleteGradingScheme")
    public void deleteNegativeGradingScheme() {
    }
}