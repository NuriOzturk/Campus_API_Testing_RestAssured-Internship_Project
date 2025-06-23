package campus;

import utilities.ParentPage;
import org.testng.annotations.*;


public class Incident extends ParentPage {

    @Test
    public void getIncident() {

    }

    @Test(dependsOnMethods = "getIncident")
    public void addIncident() {
    }

    @Test(dependsOnMethods = "addIncident")
    public void updateIncident() {
    }

    @Test(dependsOnMethods = "updateIncident")
    public void deleteIncident() {
    }

    @Test(dependsOnMethods = "deleteIncident")
    public void deleteNegativeIncident() {

    }
}