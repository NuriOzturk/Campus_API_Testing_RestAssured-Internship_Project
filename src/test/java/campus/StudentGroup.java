package campus;

import utilities.ParentPage;
import org.testng.annotations.*;

public class StudentGroup extends ParentPage {
    @Test
    public void getStudent() {

    }

    @Test(dependsOnMethods = "getStudent")
    public void createStudentGroup(){
    }

    @Test(dependsOnMethods = "createStudentGroup")
    public void updateStudentGroup(){

    }

    @Test()
    public void createStudent(){

    }

    @Test(dependsOnMethods = "createStudent")
    public void addStudentGroup(){

    }

    @Test(dependsOnMethods = "addStudentGroup")
    public void deleteStudent(){

    }

    @Test
    public void deleteStudentGroup(){

    }

    @Test(dependsOnMethods ="deleteStudentGroup")
    public void deleteNegativeStudentGroup(){

    }
}
