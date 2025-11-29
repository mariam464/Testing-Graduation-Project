package org.example;

import org.example.Pages.DependentsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DependentsTests extends SetUp {

    DependentsPage dependents;
    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        dependents = new DependentsPage();
    }

    @Test(priority = 1)
    public void testAddDependent() {
        String[] data = {"Jane Doe", "Child", "2017-05-10"};
        dependents.loginAndNavigateToDependents();
        dependents.clickAdd();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        dependents.enterDependent(data[0], data[1], data[2]);
        dependents.clickSave();
        dependents.assertAdding(data);
    }

    @Test(priority = 2)
    public void testEmptyRequiredFieldsValidation() {
        dependents.loginAndNavigateToDependents();
        dependents.clickAdd();
        dependents.clickSave();
        dependents.assertEmptyRequiredFields();
    }

    @Test(priority = 3)
    public void testEditDependent() {
        String[] data = {"Mohamed", "Child", "2016-06-15"};
        dependents.loginAndNavigateToDependents();
        dependents.clickEdit(0);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        dependents.clearAllFields();
        dependents.enterDependent(data[0], data[1], data[2]);
        dependents.clickSave();
        dependents.assertEditting(data);
    }

    @Test(priority = 4)
    public void testDeleteDependent() {
        dependents.loginAndNavigateToDependents();
        int initialCount = dependents.getRowsCount();
        dependents.clickDelete(0);
        dependents.assertDeletion(initialCount);
    }
}