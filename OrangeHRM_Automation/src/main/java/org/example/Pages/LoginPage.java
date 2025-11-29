package org.example.Pages;

import org.example.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends SetUp {
    //locators
    private By username_field = By.name("username");
    private By password_field = By.name("password");
    private By login_btn = By.className("orangehrm-login-button");

    private By inavlid_cred = By.className("oxd-alert-content-text");
    private By username_required_msg = By.cssSelector(".oxd-input-group :nth-child(3)");
    private By pasword_required_msg = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/span");


    //actions
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username_field)).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(password_field).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(login_btn).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isInvalidCredentialsMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(inavlid_cred)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameRequiredMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(username_required_msg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordRequiredMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pasword_required_msg)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //assertions
    public void assertInvalidPassword(){
        Assert.assertTrue(isInvalidCredentialsMessageDisplayed(),
                "'Invalid credentials' message not displayed for wrong password");
    }
    public void assertInvalidUsername() {
        Assert.assertTrue(isInvalidCredentialsMessageDisplayed(),
                "'Invalid credentials' message not displayed for invalid username");
    }
    public void assertEmptyCredentials(){
        softAssert.assertTrue(isUsernameRequiredMessageDisplayed(), "Username 'Required' message missing");
        softAssert.assertTrue(isPasswordRequiredMessageDisplayed(), "Password 'Required' message missing");
        softAssert.assertAll();
    }
    public void assertValidLogin(){
        DashboardPage dashboard = new DashboardPage();
        Assert.assertTrue(dashboard.isDashboardDisplayed(), "Dashboard not displayed after valid login");
    }
}