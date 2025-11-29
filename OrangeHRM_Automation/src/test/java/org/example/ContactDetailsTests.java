package org.example;

import org.example.Pages.ContactDetailsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactDetailsTests extends SetUp {

    ContactDetailsPage contact;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        contact = new ContactDetailsPage();
    }
    @Test(priority = 1)
    public void testFillAndSaveContactDetails() {
        contact.loginAndSetup();
        contact.clearAllFields();
        contact.enterAddress("123 Main", "Apt 4", "New York", "NY", "10001");
        contact.enterTelephone("111-111-1111", "222-222-2222", "333-333-3333");
        contact.enterEmail("work@example.com", "other@example.com");
        contact.clickSave();

        contact.assertUpdatingData();
    }

    @Test(priority = 2)
    public void testEmptyRequiredFieldsValidation() {
        contact.loginAndSetup();
        contact.clearAllFields();
        contact.clickSave();
        contact.assertEmptyRequiredFields();
    }

    @Test(priority = 3)
    public void testInvalidData() {
        contact.loginAndSetup();
        contact.enterAddress("123 Main St", "", "New York", "", "skskm");
        contact.clearTelephoneMobile();
        contact.clearTelephoneWork();
        contact.enterTelephone("sjs", "****", "ss$%");
        contact.enterEmail("email.example", "@gmail.com");
        contact.clickSave();
        contact.assertInvalidData();
    }
}