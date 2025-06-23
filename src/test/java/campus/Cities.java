package campus;

import utilities.ParentPage;
import org.testng.annotations.*;

public class Cities extends ParentPage {

    @Test
    public void createCities() {

    }

    @Test(dependsOnMethods = "createCities")
    public void updateCities() {

    }

    @Test(dependsOnMethods = "updateCities")
    public void deleteCities() {

    }
}
