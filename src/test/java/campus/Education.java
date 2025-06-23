package campus;

import utilities.ParentPage;
import org.testng.annotations.*;

public class Education extends ParentPage {

    @Test
    public void getEducation() {
    }

    @Test(dependsOnMethods = "getEducation")
    public void addEducation() {

    }

    @Test(dependsOnMethods = "addEducation")
    public void updateEducation() {

    }

    @Test(dependsOnMethods = "updateEducation")
    public void deleteEducation() {
    }
}