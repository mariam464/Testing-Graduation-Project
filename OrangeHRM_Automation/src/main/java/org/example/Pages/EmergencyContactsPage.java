package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class EmergencyContactsPage extends SetUp {

    private ElementUtils util;

    // locators
    public final By rows = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div");
    public final By addBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/button");
    public final By saveBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/button[2]");
    public final By requiredMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message') and normalize-space()='Required']");
    public final By requiredMsgNum = By.xpath("//span[contains(@class,'oxd-input-field-error-message') and normalize-space()='At least one phone number is required']");
    public final By errorMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
    public final By name = By.xpath("//label[contains(text(), 'Name')]/following::input[1]");
    public final By relationship = By.xpath("//label[contains(text(), 'Name')]/following::input[2]");
    public final By tel_home = By.xpath("//label[contains(text(), 'Name')]/following::input[3]");
    public final By tel_mobile = By.xpath("//label[contains(text(), 'Name')]/following::input[4]");
    public final By tel_work = By.xpath("//label[contains(text(), 'Name')]/following::input[5]");

    public final By editBtn = By.xpath("//div[@class='orangehrm-action-header']/following::button[2]");
    public final By deleteBtn = By.xpath("//div[@class='orangehrm-action-header']/following::button[1]");
    public final By alertConfirmation = By.className("oxd-button--label-danger");

    public EmergencyContactsPage() {
        this.util = new ElementUtils(driver);
    }

    public void loginAndNavigateToEmergencyContacts() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
        new MyInfoPage().goToTab("Emergency Contacts");
    }

    public void clearName() { util.robustClear(name); }
    public void clearRelationship() { util.robustClear(relationship); }
    public void clearTelHome() { util.robustClear(tel_home); }
    public void clearTelMobile() { util.robustClear(tel_mobile); }
    public void clearTelWork() { util.robustClear(tel_work); }

    public void clearAllFields() {
        clearName();
        clearRelationship();
        clearTelHome();
        clearTelMobile();
        clearTelWork();
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
    }

    public void clickEdit(int index) {
        List<WebElement> edits = driver.findElements(editBtn);
        if (!edits.isEmpty()) edits.get(index).click();
    }

    public void clickDelete(int index) {
        driver.findElement(deleteBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertConfirmation));
        driver.findElement(alertConfirmation).click();

    }

    public void enterEmergencyContact(String name, String relationship, String homeTel, String mobileTel, String workTel) {
        util.robustClear(this.name); driver.findElement(this.name).sendKeys(name);
        util.robustClear(this.relationship); driver.findElement(this.relationship).sendKeys(relationship);
        util.robustClear(tel_home); driver.findElement(tel_home).sendKeys(homeTel);
        util.robustClear(tel_mobile); driver.findElement(tel_mobile).sendKeys(mobileTel);
        util.robustClear(tel_work); driver.findElement(tel_work).sendKeys(workTel);
    }

    public int getRequiredMessagesCount() {
        return (driver.findElements(requiredMsg).size() + driver.findElements(requiredMsgNum).size());
    }
    public int getErrorMessagesCount(){
        return driver.findElements(errorMsg).size();
    }
    public int getRowsCount(){
        return driver.findElements(rows).size();
    }
    public boolean isContactPresent(String name, String relationship, String homeTel, String mobileTel, String workTel) {
        List<WebElement> rowsList = driver.findElements(this.rows);

        for (WebElement row : rowsList) {
            String rowText = row.getText();
            if (rowText.contains(name) &&
                    rowText.contains(relationship) &&
                    rowText.contains(homeTel) &&
                    rowText.contains(mobileTel) &&
                    rowText.contains(workTel)){
                return true;
            }
        }
        return false;
    }

    // assertions
    public void assertAddingContact(String[] data){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertTrue(isContactPresent(data[0], data[1], data[2], data[3], data[4]), "Emergency Contact not added");
    }
    public void assertEmptyRequiredFields(){
        int requiredCount = getRequiredMessagesCount();
        Assert.assertEquals(requiredCount, 3, "Number of required messages is NOT 3!");
    }
    public void assertInvalidData(){
        int requiredCount = getRequiredMessagesCount();
        Assert.assertEquals(requiredCount, 3, "Number of required messages is NOT 3!");
    }
    public void assertEditting(String[] data){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertTrue(isContactPresent(data[0], data[1], data[2], data[3], data[4]), "Contact not updated");
    }
    public void assertDeletion(int initialCount){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        int finalCount = getRowsCount();
        Assert.assertTrue(finalCount < initialCount, "Contact isn't deleted");
    }
}