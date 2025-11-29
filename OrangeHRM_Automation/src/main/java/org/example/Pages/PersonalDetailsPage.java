package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class PersonalDetailsPage extends SetUp {

    private ElementUtils utils;

    // --- Locators ---
    private final By spinner = By.className("oxd-form-loader");

    public final By requiredMsg = By.xpath("//span[contains(@class, 'oxd-input-field-error-message') and normalize-space()='Required']");
    public final By firstName = By.name("firstName");
    public final By middleName = By.name("middleName");
    public final By lastName = By.name("lastName");

    public final By empId = By.xpath("//label[contains(text(),'Employee Id')]/following::input[1]");
    public final By otherId = By.xpath("//label[contains(text(),'Other Id')]/following::input[1]");
    public final By licenseNum = By.xpath("//label[contains(text(),'Number')]/following::input[1]");
    public final By licenseExpiry = By.xpath("//label[contains(text(),'Expiry')]/following::input[1]");

    public final By nationality = By.xpath("//label[contains(text(), 'Nationality')]/following::input[1]");
    public final By dateOfBirth = By.xpath("//label[contains(text(), 'Birth')]/following::input[1]");

    public final By gender_male = By.xpath("//label[contains(text(), 'Gender')]/following::input[1]");
    public final By gender_female = By.xpath("//label[contains(text(), 'Gender')]/following::input[2]");

    public final By saveBtn = By.xpath("//label[text()='Employee Id']/ancestor::form//button[@type='submit']");

    // --- Constructor ---
    public PersonalDetailsPage() {
        if (driver == null) {
            throw new IllegalStateException("Driver is null. Make sure 'PersonalDetailsPage personal;' is declared at the top, but 'personal = new PersonalDetailsPage();' is inside @BeforeMethod.");
        }
        this.utils = new ElementUtils(driver);
    }

    // --- Helpers ---
    private void waitForSpinner() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        } catch (Exception e) {
            // Ignore
        }
    }

    // --- Actions ---
    // Renamed to avoid confusion with SetUp.setUp()
    public void loginAndSetup() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();

        waitForSpinner();
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public void enterFullName(String first, String middle, String last) {
        waitForSpinner();
        utils.robustClear(firstName);
        driver.findElement(firstName).sendKeys(first);

        utils.robustClear(middleName);
        driver.findElement(middleName).sendKeys(middle);

        utils.robustClear(lastName);
        driver.findElement(lastName).sendKeys(last);
    }

    public void enterEmployeeIds(String employeeId, String otherId) {
        waitForSpinner();
        driver.findElement(empId).clear();
        driver.findElement(empId).sendKeys(employeeId);
        driver.findElement(this.otherId).clear();
        driver.findElement(this.otherId).sendKeys(otherId);
    }

    public void setLicenseInfo(String number, String expiry) {
        waitForSpinner();
        driver.findElement(licenseNum).clear();
        driver.findElement(licenseNum).sendKeys(number);
        driver.findElement(licenseExpiry).clear();
        driver.findElement(licenseExpiry).sendKeys(expiry);
    }

    public void selectNationality(String text) {
        waitForSpinner();
        driver.findElement(nationality).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + text + "']"))).click();
    }

    public void setDateOfBirth(String dob) {
        waitForSpinner();
        driver.findElement(dateOfBirth).clear();
        driver.findElement(dateOfBirth).sendKeys(dob);
    }

    public void selectGender(String gender) {
        waitForSpinner();
        if (gender.equalsIgnoreCase("male"))
            driver.findElement(gender_male).click();
        else
            driver.findElement(gender_female).click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        waitForSpinner();
    }

    public boolean requiredMessageVisible() {
        return !driver.findElements(requiredMsg).isEmpty();
    }

    public String getEmployeeIdValue() {
        return driver.findElement(empId).getAttribute("value");
    }

    public String getOtherIdValue() {
        return driver.findElement(otherId).getAttribute("value");
    }

    public String getEmployeeFirstName() {
        return driver.findElement(firstName).getAttribute("value");
    }

    public String getEmployeeMiddleName() {
        return driver.findElement(middleName).getAttribute("value");
    }

    public String getEmployeeLastName() {
        return driver.findElement(lastName).getAttribute("value");
    }

    public String getDateofBirthValue() {
        return driver.findElement(dateOfBirth).getAttribute("value");
    }

    public void clearFullName() {
        waitForSpinner();
        utils.robustClear(firstName);
        utils.robustClear(lastName);
        utils.robustClear(middleName);
    }

    public void clearDob() {
        waitForSpinner();
        utils.robustClear(dateOfBirth);
    }

    public void clearLicenseInfo() {
        waitForSpinner();
        utils.robustClear(licenseNum);
        utils.robustClear(licenseExpiry);
    }

    public void clearIds() {
        waitForSpinner();
        utils.robustClear(empId);
        utils.robustClear(otherId);
    }

    // assertion
    public void assertEmptyRequiredFields() {
        int requiredCount = driver.findElements(requiredMsg).size();
        Assert.assertEquals(requiredCount, 5, "Number of required messages is NOT 5!");
    }

    public void assertInvalidData(String invalidName, String invalidEmpId, String invalidOtherId, String invalidDOB) {
        softAssert.assertNotEquals(getEmployeeFirstName(), invalidName, "Invalid First Name was saved!");
        softAssert.assertNotEquals(getEmployeeMiddleName(), invalidName, "Invalid Middle Name was saved!");
        softAssert.assertNotEquals(getEmployeeLastName(), invalidName, "Invalid Name was saved!");
        softAssert.assertNotEquals(getEmployeeIdValue(), invalidEmpId, "Invalid Employee ID was saved!");
        softAssert.assertNotEquals(getOtherIdValue(), invalidOtherId, "Invalid Other ID was saved!");
        softAssert.assertNotEquals(getDateofBirthValue(), invalidDOB, "Invalid Date of Birth was saved!");
        softAssert.assertAll();
    }
}