package org.example;

import org.example.Pages.AddEmpPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddEmployeeTests extends SetUp {

    AddEmpPage addEmployeePage;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        addEmployeePage = new AddEmpPage();
    }

    @Test(priority = 1)
    public void testAddEmployeeBasic() {
        addEmployeePage.loginAndNavigateToAddEmployee();

        String empId = "998877";
        addEmployeePage.enterName("John", "Basic", "Doe");
        addEmployeePage.enterEmployeeId(empId);
        addEmployeePage.clickSave();
        addEmployeePage.assertEmployeeAdded();
    }

    @Test(priority = 2)
    public void testAddEmployeeWithLoginDetails() {
        addEmployeePage.loginAndNavigateToAddEmployee();

        String empId = "998878";
        String username = "johndoe" + System.currentTimeMillis();

        addEmployeePage.enterName("John", "Login", "Doe");
        addEmployeePage.enterEmployeeId(empId);

        addEmployeePage.toggleCreateLoginDetails(true);
        addEmployeePage.enterLoginDetails(username, "Password@123", "Password@123");

        addEmployeePage.clickSave();

        addEmployeePage.assertEmployeeAdded();
    }
}