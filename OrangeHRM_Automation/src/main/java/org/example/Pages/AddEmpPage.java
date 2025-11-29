package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AddEmpPage extends SetUp {

    private ElementUtils util;

    private final By spinner = By.className("oxd-form-loader");

    private final By addEmployeeNavLink = By.xpath("//a[normalize-space()='Add Employee']");
    private final By firstName = By.name("firstName");
    private final By middleName = By.name("middleName");
    private final By lastName = By.name("lastName");
    private final By employeeId = By.xpath("//label[contains(text(), 'Employee Id')]/following::input[1]");
    private final By photoUpload = By.xpath("//input[@type='file']");

    private final By createLoginCheckbox = By.xpath("//div[@class='oxd-switch-wrapper']//label");
    private final By username = By.xpath("//label[contains(text(), 'Username')]/following::input[1]");
    private final By password = By.xpath("//label[contains(text(), 'Password')]/following::input[1]");
    private final By confirmPassword = By.xpath("//label[contains(text(), 'Confirm Password')]/following::input[1]");

    private final By saveBtn = By.xpath("//button[@type='submit']");
    private final By cancelBtn = By.xpath("//button[contains(., 'Cancel')]");

    private final By successToast = By.xpath("//div[@id='oxd-toaster_1']");
    public AddEmpPage() {
        if (driver == null) {
            throw new IllegalStateException("Driver is null. Initialize AddEmployeePage inside @BeforeMethod.");
        }
        this.util = new ElementUtils(driver);
    }

    private void waitForSpinner() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        } catch (Exception e) {}
    }

    public void loginAndNavigateToAddEmployee() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToPIM();
        waitForSpinner();
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeNavLink)).click();
        waitForSpinner();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public void enterName(String first, String middle, String last) {
        util.robustClear(firstName); driver.findElement(firstName).sendKeys(first);
        util.robustClear(middleName); driver.findElement(middleName).sendKeys(middle);
        util.robustClear(lastName); driver.findElement(lastName).sendKeys(last);
    }

    public void enterEmployeeId(String id) {
        util.robustClear(employeeId);
        driver.findElement(employeeId).sendKeys(id);
    }

    public void uploadPhoto(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            driver.findElement(photoUpload).sendKeys(filePath);
        }
    }

    public void toggleCreateLoginDetails(boolean enable) {
        if (enable) {
            driver.findElement(createLoginCheckbox).click();
            // Wait for username field to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        }
    }

    public void enterLoginDetails(String user, String pass, String confirmPass) {
        util.robustClear(username); driver.findElement(username).sendKeys(user);
        util.robustClear(password); driver.findElement(password).sendKeys(pass);
        util.robustClear(confirmPassword); driver.findElement(confirmPassword).sendKeys(confirmPass);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        waitForSpinner();
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
    }

    public void assertEmployeeAdded() {
        waitForSpinner();
        Assert.assertTrue(driver.getCurrentUrl().contains("viewPersonalDetails"), "Did not redirect to Personal Details page after saving!");
    }
}