package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class ImmigrationPage extends SetUp {

    private ElementUtils util;

    // locators
    public final By rows = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div");
    public final By addBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/button");
    public final By saveBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/button[2]");
    public final By requiredMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message') and normalize-space()='Required']");

    public final By number = By.xpath("//label[contains(text(), 'Number')]/following::input[1]");
    public final By issuedDate = By.xpath("//label[contains(text(), 'Number')]/following::input[2]");
    public final By expiryDate = By.xpath("//label[contains(text(), 'Number')]/following::input[3]");
    public final By eligibleStatus = By.xpath("//label[contains(text(), 'Number')]/following::input[4]");
    public final By issuedBy = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div/div[5]/div/div[2]/div/div/div[1]");
    public final By eligibleReviewDate = By.xpath("//label[contains(text(), 'Number')]/following::input[5]");
    public final By comment = By.tagName("textarea");
    public final By errorMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
    public final By editBtn = By.xpath("//div[@class='orangehrm-action-header']/following::button[2]");
    public final By deleteBtn = By.xpath("//div[@class='orangehrm-action-header']/following::button[1]");
    public final By alertConfirmation = By.className("oxd-button--label-danger");

    public ImmigrationPage() {
        this.util = new ElementUtils(driver);
    }

    // actions
    public void loginAndNavigateToImmigration() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
        new MyInfoPage().goToTab("Immigration");
    }

    public void clearNumber() { util.robustClear(number); }
    public void clearIssuedDate() { util.robustClear(issuedDate); }
    public void clearExpiryDate() { util.robustClear(expiryDate); }
    public void clearEligibleStatus() { util.robustClear(eligibleStatus); }
    public void clearEligibleReviewDate() { util.robustClear(eligibleReviewDate); }
    public void clearComments() { util.robustClear(comment); }

    public void clearAllFields() {
        clearNumber();
        clearIssuedDate();
        clearExpiryDate();
        clearEligibleStatus();
        clearEligibleReviewDate();
        clearComments();
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
        List<WebElement> deletes = driver.findElements(deleteBtn);
        if (!deletes.isEmpty()) {
            deletes.get(index).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(alertConfirmation));
            driver.findElement(alertConfirmation).click();
        }
    }

    public void enterImmigration(String number, String issuedDate, String expiryDate,
                                 String eligibleStatus, String issuedBy, String reviewDate, String comments) {

        util.robustClear(this.number); driver.findElement(this.number).sendKeys(number);
        util.robustClear(this.issuedDate); driver.findElement(this.issuedDate).sendKeys(issuedDate);
        util.robustClear(this.expiryDate); driver.findElement(this.expiryDate).sendKeys(expiryDate);
        util.robustClear(this.eligibleStatus); driver.findElement(this.eligibleStatus).sendKeys(eligibleStatus);

        driver.findElement(this.issuedBy).click();
        WebElement issuedByOption = driver.findElement(By.xpath("//span[text()='" + issuedBy + "']"));
        issuedByOption.click();

        util.robustClear(eligibleReviewDate); driver.findElement(eligibleReviewDate).sendKeys(reviewDate);
        util.robustClear(comment); driver.findElement(comment).sendKeys(comments);
    }

    public int getRequiredMessagesCount() {
        return driver.findElements(requiredMsg).size();
    }

    public int getErrorMessagesCount(){
        return driver.findElements(requiredMsg).size();
    }

    public int getRowsCount(){
        return driver.findElements(rows).size();
    }

    public boolean isImmigrationRecordPresent(String number, String issuedDate, String expiryDate, String eligibleStatus, String issuedBy, String reviewDate, String comments) {
        List<WebElement> rowsList = driver.findElements(this.rows);

        for (WebElement row : rowsList) {
            String rowText = row.getText();
            if (rowText.contains(number) &&
                    rowText.contains(issuedDate) &&
                    rowText.contains(expiryDate) &&
                    rowText.contains(issuedBy)){
                return true;
            }
        }
        return false;
    }

    // assertion
    public void assertAdding(String[] data){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertTrue(isImmigrationRecordPresent(data[0], data[1], data[2],
                data[3], data[4], data[5], data[6]), "Immigration record not added");
    }

    public void assertEmptyRequiredFields(){
        int requiredCount = getRequiredMessagesCount();
        Assert.assertEquals(requiredCount, 5, "Number of required messages is NOT 5!");
    }

    public void assertEditting(String[] data){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertTrue(isImmigrationRecordPresent(data[0], data[1], data[2],
                data[3], data[4], data[5], data[6]), "Immigration record not updated");
    }

    public void assertDeletion(int initialCount){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        int finalCount = getRowsCount();
        Assert.assertTrue(finalCount < initialCount, "Immigration Record not deleted");
    }
}