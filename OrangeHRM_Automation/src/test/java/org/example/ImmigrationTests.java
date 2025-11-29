package org.example;

import org.example.Pages.ImmigrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ImmigrationTests extends SetUp {

    ImmigrationPage immigration;
    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        immigration = new ImmigrationPage();
    }

    @Test(priority = 1)
    public void testAddImmigration() {
        String[] data = {"A1234567", "2020-01-01", "2030-01-01",
                "Valid", "Egypt", "2030-01-01", "Type Comments here"};

        immigration.loginAndNavigateToImmigration();
        immigration.clickAdd();
        immigration.enterImmigration(
                data[0], data[1], data[2],
                data[3], data[4], data[5], data[6]
        );
        immigration.clickSave();
        immigration.assertAdding(data);
    }

    @Test(priority = 2)
    public void testEmptyRequiredFieldsValidation() {
        immigration.loginAndNavigateToImmigration();
        immigration.clickAdd();
        immigration.clickSave();
        immigration.assertEmptyRequiredFields();
    }

    @Test(priority = 3)
    public void testEditImmigration() {
        String[] data = {"A1234567", "2021-11-01", "2030-01-01", "Invalidsjjs", "Egypt", "2030-01-01", "Type Comments herekskssksksssmmsssksksm"};
        immigration.loginAndNavigateToImmigration();
        immigration.clickEdit(0);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        immigration.clearEligibleStatus();
        immigration.clearNumber();
        immigration.enterImmigration(data[0], data[1], data[2],
                data[3], data[4], data[5], data[6]);
        immigration.clickSave();
        immigration.assertEditting(data);
    }

    @Test(priority = 4)
    public void testDeleteImmigration() {
        immigration.loginAndNavigateToImmigration();
        int initialCount = immigration.getRowsCount();
        immigration.clickDelete(0);
        immigration.assertDeletion(initialCount);
    }
}