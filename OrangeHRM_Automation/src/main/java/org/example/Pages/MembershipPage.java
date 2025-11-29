package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MembershipPage extends SetUp {

    private ElementUtils util;
    private final By spinner = By.className("oxd-form-loader");

    private final By addBtn = By.xpath("//button[contains(., 'Add')]");
    private final By saveBtn = By.xpath("//button[@type='submit']");
    private final By deleteConfirmBtn = By.xpath("//button[contains(@class, 'oxd-button--label-danger')]");

    private final By subAmount = By.xpath("//label[contains(text(), 'Subscription Amount')]/following::input[1]");
    private final By subCommenceDate = By.xpath("//label[contains(text(), 'Commence Date')]/following::input[1]");
    private final By subRenewalDate = By.xpath("//label[contains(text(), 'Renewal Date')]/following::input[1]");

    public MembershipPage() {
        if (driver == null) {
            throw new IllegalStateException("Driver is null. Initialize MembershipsPage inside @BeforeMethod.");
        }
        this.util = new ElementUtils(driver);
    }
    private void waitForSpinner() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        } catch (Exception e) {}
    }

    private void selectDropdownOption(String labelName, String optionText) {
        By dropdown = By.xpath("//label[contains(text(), '" + labelName + "')]/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By option = By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    private void clickActionIcon(String uniqueText, String iconClassSuffix) {
        waitForSpinner();
        String xpath = String.format("//div[@role='row' and contains(., '%s')]//button[.//i[contains(@class, '%s')]]", uniqueText, iconClassSuffix);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public void loginAndNavigateToMemberships() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
        waitForSpinner();
        new MyInfoPage().goToTab("Memberships");
        waitForSpinner();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(addBtn));
    }

    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        waitForSpinner();
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        waitForSpinner();
    }

    public void clickEdit(String membershipName) {
        clickActionIcon(membershipName, "bi-pencil-fill");
        waitForSpinner();
    }

    public void clickDelete(String membershipName) {
        clickActionIcon(membershipName, "bi-trash");
        wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmBtn)).click();
        waitForSpinner();
    }

    public void addMembership(String membership, String paidBy, String amount, String currency, String commence, String renewal) {
        clickAdd();

        selectDropdownOption("Membership", membership);
        selectDropdownOption("Subscription Paid By", paidBy);
        util.robustClear(subAmount); driver.findElement(subAmount).sendKeys(amount);
        selectDropdownOption("Currency", currency);
        util.robustClear(subCommenceDate); driver.findElement(subCommenceDate).sendKeys(commence);
        util.robustClear(subRenewalDate); driver.findElement(subRenewalDate).sendKeys(renewal);

        clickSave();
    }

    public void updateMembership(String membership, String newAmount) {
        clickEdit(membership);
        util.robustClear(subAmount); driver.findElement(subAmount).sendKeys(newAmount);
        clickSave();
    }

    public boolean isMembershipDisplayed(String membershipName) {
        waitForSpinner();
        String xpath = "//div[@role='row' and contains(., '" + membershipName + "')]";
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public void assertMembershipAdded(String membership) {
        Assert.assertTrue(isMembershipDisplayed(membership), "Membership '" + membership + "' not found.");
    }

    public void assertMembershipDeleted(String membership) {
        Assert.assertFalse(isMembershipDisplayed(membership), "Membership '" + membership + "' STILL found after deletion.");
    }
}