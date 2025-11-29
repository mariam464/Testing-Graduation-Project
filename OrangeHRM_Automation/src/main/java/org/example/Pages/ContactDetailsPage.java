package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ContactDetailsPage extends SetUp {

    private ElementUtils util;

    // locators
    public final By saveBtn = By.xpath("//button[@type='submit']");
    public final By requiredMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message') and normalize-space()='Required']");
    public final By errorMsg = By.xpath("//span[contains(@class, 'oxd-input-field-error-message')]");

    public final By street1 = By.xpath("//label[contains(text(), 'Street 1')]/following::input[1]");
    public final By street2 = By.xpath("//label[contains(text(), 'Street 1')]/following::input[2]");
    public final By city = By.xpath("//label[contains(text(), 'Street 1')]/following::input[3]");
    public final By state = By.xpath("//label[contains(text(), 'Street 1')]/following::input[4]");
    public final By _ZIP = By.xpath("//label[contains(text(), 'Street 1')]/following::input[5]");
    public final By country = By.xpath("//label[text()='Country']/following::div[1]");

    public final By tel_home = By.xpath("//label[contains(text(), 'Street 1')]/following::input[6]");
    public final By tel_mobile = By.xpath("//label[contains(text(), 'Street 1')]/following::input[7]");
    public final By tel_work = By.xpath("//label[contains(text(), 'Street 1')]/following::input[8]");

    public final By email_work = By.xpath("//label[contains(text(), 'Street 1')]/following::input[9]");
    public final By email_other = By.xpath("//label[contains(text(), 'Street 1')]/following::input[10]");

    public ContactDetailsPage() {
        this.util = new ElementUtils(driver);
    }

    // actions
    public void loginAndSetup() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
        new MyInfoPage().goToTab("Contact Details");
    }

    public void clearStreet1() { util.robustClear(street1); }
    public void clearStreet2() { util.robustClear(street2); }
    public void clearCity() { util.robustClear(city); }
    public void clearState() { util.robustClear(state); }
    public void clearZip() { util.robustClear(_ZIP); }
    public void clearTelephoneHome() { util.robustClear(tel_home); }
    public void clearTelephoneMobile() { util.robustClear(tel_mobile); }
    public void clearTelephoneWork() { util.robustClear(tel_work); }
    public void clearEmailWork() { util.robustClear(email_work); }
    public void clearEmailOther() { util.robustClear(email_other); }

    public void clearAllFields() {
        clearStreet1();
        clearStreet2();
        clearCity();
        clearState();
        clearZip();
        clearTelephoneHome();
        clearTelephoneMobile();
        clearTelephoneWork();
        clearEmailWork();
        clearEmailOther();
    }

    public void clearCountry() {
        driver.findElement(country).click();
        WebElement placeholder = driver.findElement(By.xpath("//span[text()='-- Select --']"));
        placeholder.click();
    }

    public void enterAddress(String street1, String street2, String city, String state, String zip) {
        util.robustClear(this.street1); driver.findElement(this.street1).sendKeys(street1);
        util.robustClear(this.street2); driver.findElement(this.street2).sendKeys(street2);
        util.robustClear(this.city); driver.findElement(this.city).sendKeys(city);
        util.robustClear(this.state); driver.findElement(this.state).sendKeys(state);
        util.robustClear(_ZIP); driver.findElement(_ZIP).sendKeys(zip);
    }

    public void enterTelephone(String home, String mobile, String work) {
        util.robustClear(tel_home); driver.findElement(tel_home).sendKeys(home);
        util.robustClear(tel_mobile); driver.findElement(tel_mobile).sendKeys(mobile);
        util.robustClear(tel_work); driver.findElement(tel_work).sendKeys(work);
    }

    public void enterEmail(String work, String other) {
        util.robustClear(email_work); driver.findElement(email_work).sendKeys(work);
        util.robustClear(email_other); driver.findElement(email_other).sendKeys(other);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public int getRequiredMessagesCount() {
        return driver.findElements(requiredMsg).size();
    }

    public String getStreet1() {
        return driver.findElement(street1).getAttribute("value");
    }

    public int getErrorMessage() {
        return driver.findElements(errorMsg).size();
    }

    // assertions
    public void assertUpdatingData(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertEquals(getStreet1(), "123 Main");
    }

    public void assertEmptyRequiredFields(){
        int requiredCount = getRequiredMessagesCount();
        Assert.assertEquals(requiredCount, 5, "Number of required messages is NOT 5!");
    }

    public void assertInvalidData(){
        int requiredCount = getErrorMessage();
        Assert.assertTrue(requiredCount == 6, "Validation error messages appeared ="+ requiredCount);
    }
}