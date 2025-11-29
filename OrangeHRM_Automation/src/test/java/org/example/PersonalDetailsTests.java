package org.example;

import org.example.Pages.PersonalDetailsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonalDetailsTests extends SetUp {

    PersonalDetailsPage personal;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initializePage() {
        personal = new PersonalDetailsPage();
    }

    @Test(priority = 1)
    public void updatePersonalDetails(){
        personal.loginAndSetup();

        personal.clearFullName();
        personal.clearIds();
        personal.clearLicenseInfo();
        personal.clearDob();

        personal.enterFullName("John", "Middle", "Doe");
        personal.enterEmployeeIds("12345", "292829");
        personal.setLicenseInfo("ABCD12345", "2030-12-10");
        personal.setDateOfBirth("1995-21-10");
        personal.clickSave();
    }

    @Test(priority = 2)
    public void validateEmptyRequiredFields() {
        personal.loginAndSetup();

        personal.clearFullName();
        personal.clearIds();
        personal.clickSave();

        personal.assertEmptyRequiredFields();
    }

    @Test(priority = 3)
    public void validateInvalidData() {
        String invalidName = "2882*";
        String invalidEmpId = "@@@$%";
        String invalidOtherId = "ssksnd";
        String invalidDOB = "2027-21-10";

        personal.loginAndSetup();

        personal.clearFullName();
        personal.clearIds();
        personal.clearDob();

        personal.enterFullName("2882*", "2882*", "2882*");
        personal.enterEmployeeIds(invalidEmpId, invalidOtherId);
        personal.setDateOfBirth("2027-21-10");
        personal.clickSave();

        personal.assertInvalidData(invalidName, invalidEmpId, invalidOtherId, invalidDOB);
    }
}