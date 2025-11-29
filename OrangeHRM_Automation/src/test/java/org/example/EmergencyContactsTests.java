package org.example;

import org.example.Pages.EmergencyContactsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmergencyContactsTests extends SetUp {

    EmergencyContactsPage emergency;
    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        emergency = new EmergencyContactsPage();
    }

    @Test(priority = 1)
    public void testAddEmergencyContact() {
        emergency.loginAndNavigateToEmergencyContacts();
        emergency.clickAdd();
        String[] data = {"John Doe","Brother", "111-111-1111", "222-222-2222", "333-333-3333"};
        emergency.enterEmergencyContact(data[0], data[1], data[2], data[3], data[4]);
        emergency.clickSave();
        emergency.assertAddingContact(data);
    }

    @Test(priority = 2)
    public void testEmptyRequiredFieldsValidation() {
        emergency.loginAndNavigateToEmergencyContacts();
        emergency.clickAdd();
        emergency.clickSave();
        emergency.assertEmptyRequiredFields();
    }

    @Test(priority = 3)
    public void testInvalidData() {
        emergency.loginAndNavigateToEmergencyContacts();
        emergency.clickAdd();
        emergency.enterEmergencyContact("28282**", "kssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss", "skss**", "**sjjs", "sjs^^");
        emergency.clickSave();
        emergency.assertInvalidData();
    }

    @Test(priority = 4)
    public void testEditEmergencyContact() {
        String[] data = {"Mariam", "Sister", "", "999-999-9999", ""};
        emergency.loginAndNavigateToEmergencyContacts();
        emergency.clickEdit(0);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        emergency.enterEmergencyContact(data[0], data[1], data[2], data[3], data[4]);
        emergency.clickSave();
        emergency.assertEditting(data);
    }

    @Test(priority = 5)
    public void testDeleteEmergencyContact() {
        emergency.loginAndNavigateToEmergencyContacts();
        int initialCount = emergency.getRowsCount();
        emergency.clickDelete(0);
        emergency.assertDeletion(initialCount);
    }
}