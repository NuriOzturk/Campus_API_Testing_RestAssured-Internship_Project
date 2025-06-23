package campus;

import utilities.ParentPage;
import org.testng.annotations.*;

public class States extends ParentPage {

    @Test
    public void createState() {

    }

    @Test(dependsOnMethods = "createState")
    public void updateState() {
    }

    @Test(dependsOnMethods = "updateState")
    public void deleteState() {
    }
}
