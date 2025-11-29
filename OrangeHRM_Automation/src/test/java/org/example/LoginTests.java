package org.example;

import org.example.Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTests extends SetUp {

    LoginPage login = new LoginPage();
    private String validUsername = "mariam29";
    private String validPassword = "Mariam@2982006";

    @Test(description = "Verify that user can login with valid credentials")
    public void testValidLogin() {
        login.login(validUsername, validPassword);
        login.assertValidLogin();
    }

    @Test(description = "Verify login fails with invalid password")
    public void testInvalidPassword() {
        String invalidPass = "WrongPass123";
        login.login(validUsername, invalidPass);
        login.assertInvalidPassword();
    }

    @Test(description = "Verify login fails with invalid username")
    public void testInvalidUsername() {
        String invalid_username = "FakeUser";
        login.login(invalid_username, validPassword);
        login.assertInvalidUsername();
    }

    @Test(description = "Verify login fails when username and password are empty")
    public void testEmptyLogin() {
        login.login("", "");
        login.assertEmptyCredentials();
    }
}
