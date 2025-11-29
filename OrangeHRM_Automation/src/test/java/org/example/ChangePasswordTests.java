package org.example;

import org.example.Pages.ChangePasswordPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangePasswordTests extends SetUp {

    ChangePasswordPage changePass = new ChangePasswordPage();
    @DataProvider(name = "passwordNegativeData")
    public Object[][] passwordNegativeData() {
        return new Object[][]{
                {"Mariam@2982006", "", "", "REQUIRED_FIELD"},                // all empty
                {"wrongPass", "Valid1!", "Valid1!", "WRONG_CURRENT"},  // wrong current password
                {"Mariam@2982006", "short", "short", "TOO_SHORT"},          // too short
                {"Mariam@2982006", "VeryLongPassword1234567890!VeryLongPassword1234567890VeryLongPassword1234567890VeryLongPassword1234567890VeryLongPassword1234567890VeryLongPassword12345678VeryLongPassword1234567890VeryLongPassword12345678909v0", "VeryLongPassword1234567890VeryLongPassword1234567890VeryLongPassword1234567890VeryLongPassword1234567890VeryLongPassword12345678VeryLongPassword1234567890VeryLongPassword12345678909v0!", "TOO_LONG"}, // too long
                {"Mariam@2982006", "lettersOnly", "lettersOnly", "COMPLEXITY"}, // complexity fail
                {"Mariam@2982006", "Valid1!", "Mismatch1!", "MISMATCH"},    // new ≠ confirm
                {"Mariam@2982006", "Mariam@2982006", "Mariam@2982006", "SAME_AS_OLD"}   // same as current
        };
    }

    @Test(dataProvider = "passwordNegativeData")
    public void testChangePasswordNegative(String currentPass, String newPass, String confirmPass, String errorType) {
        changePass.setup();
        changePass.changePassword(currentPass, newPass, confirmPass);
        By locator = null;
        switch (errorType) {
            case "REQU" +
                         "IRED_FIELD":
                locator = changePass.getRequiredFieldMsg();
                break;
            case "WRONG_CURRENT":
                locator = changePass.getWrongCurrentFieldMsgFieldMsg();
                break;
            case "TOO_SHORT":
                locator = changePass.getPasswordTooShortMsg();
                break;
            case "TOO_LONG":
                locator = changePass.getPasswordTooLongMsg();
                break;
            case "COMPLEXITY":
                locator = changePass.getPasswordComplexityMsg();
                break;
            case "MISMATCH":
                locator = changePass.getPasswordMismatchMsg();
                break;
            case "SAME_AS_OLD":
                locator = By.xpath("//span[contains(text(),'New password cannot be same as old password')]");
                break;
        }

        Assert.assertTrue(changePass.isErrorMessageDisplayed(locator),
                "Expected validation message not displayed for scenario: " + errorType);
    }
}
