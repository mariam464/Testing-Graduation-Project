package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class DependentsPage extends SetUp {

    private ElementUtils util;

    // locators
    public final By rows = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/div/div");
    public final By addBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/button");
    public final By saveBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/button[2]");
    public final By requiredMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message') and normalize-space()='Required']");
    public final By errorMsg = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

    public final By name = By.xpath("//label[contains(text(), 'Name')]/following::input[1]");
    public final By relationship = By.className("oxd-select-text-input");
    public final By dateOfBirth = By.xpath("//label[contains(text(), 'Name')]/following::input[2]");

    public final By editBtn = By.xpath("//div[@class='orangehrm-action-header']/following::button[2]");
    public final By deleteBtn = By.xpath("//div[@class='orangehrm-action-header']/following::button[1]");
    public final By alertConfirmation = By.className("oxd-button--label-danger");

    public DependentsPage() {
        this.util = new ElementUtils(driver);
    }

    // actions
    public void loginAndNavigateToDependents() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
        new MyInfoPage().goToTab("Dependents");
    }

    public void clearName() {
        util.robustClear(name);
    }

    public void clearDateOfBirth() {
        util.robustClear(dateOfBirth);
    }

    public void clearAllFields() {
        clearName();
        clearDateOfBirth();
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

    public void enterDependent(String name, String relationship, String dob) {
        util.robustClear(this.name); driver.findElement(this.name).sendKeys(name);

        driver.findElement(this.relationship).click();
        WebElement option = driver.findElement(By.xpath("//span[text()='" + relationship + "']"));
        option.click();

        util.robustClear(dateOfBirth);
        driver.findElement(dateOfBirth).sendKeys(dob);
    }

    public int getRequiredMessagesCount() {
        return driver.findElements(requiredMsg).size();
    }

    public int getErrorMessagesCount(){
        return driver.findElements(errorMsg).size();
    }

    public int getRowsCount(){
        return driver.findElements(rows).size();
    }

    public boolean isDependentPresent(String name, String relationship, String dob) {
        List<WebElement> rowsList = driver.findElements(this.rows);

        for (WebElement row : rowsList) {
            String rowText = row.getText();
            if (rowText.contains(name) &&
                    rowText.contains(relationship) &&
                    rowText.contains(dob)) {
                return true;
            }
        }
        return false;
    }

    // assertions
    public void assertAdding(String[] data){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertTrue(isDependentPresent(data[0], data[1], data[2]), "Dependent not added");
    }

    public void assertEmptyRequiredFields(){
        int requiredCount = getRequiredMessagesCount();
        Assert.assertEquals(requiredCount, 2, "Number of required messages is NOT 2!");
    }

    public void assertEditting(String[] data){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        Assert.assertTrue(isDependentPresent(data[0], data[1], data[2]), "Dependent isn't updated");
    }

    public void assertDeletion(int initialCount){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));
        int finalCount = getRowsCount();
        Assert.assertTrue(finalCount < initialCount, "Dependent isn't deleted");
    }
}