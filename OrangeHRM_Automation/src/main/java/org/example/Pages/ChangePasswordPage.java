package org.example.Pages;

import org.example.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangePasswordPage extends SetUp {

    // locators
    private final By currentPass = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/input");
    private final By newPass = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private final By confirmPass = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private final By saveBtn = By.cssSelector(".oxd-button:nth-of-type(2)");

    private final By requiredMsg = By.xpath("//span[contains(@class, 'oxd-input-field-error-message') and normalize-space()='Required']");
    private final By wrongCurrentPassMsg = By.xpath("//*[@id=\"oxd-toaster_1\"]");
    private final By passMismatchMsg = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/span");
    private final By shartPassMsg = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/span");
    private final By longPassMsg = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/span");
    private final By complexPassMsg = By.xpath("//span[contains(normalize-space(.), 'must contain')]");

    public ChangePasswordPage() {
    }

    // Actions
    public void setup() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new UserMenuList().navigateToChangePassword();
    }

    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentPass)).sendKeys(currentPassword);
        driver.findElement(newPass).sendKeys(newPassword);
        driver.findElement(confirmPass).sendKeys(confirmPassword);
        driver.findElement(saveBtn).click();
    }

    public boolean isErrorMessageDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public By getRequiredFieldMsg() { return requiredMsg; }
    public By getWrongCurrentFieldMsgFieldMsg() { return wrongCurrentPassMsg; }
    public By getPasswordMismatchMsg() { return passMismatchMsg; }
    public By getPasswordTooShortMsg() { return shartPassMsg; }
    public By getPasswordTooLongMsg() { return longPassMsg; }
    public By getPasswordComplexityMsg() { return complexPassMsg; }
}