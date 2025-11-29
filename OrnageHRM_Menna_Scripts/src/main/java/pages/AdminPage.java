package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {

    WebDriver driver;
    WebDriverWait wait;

    public AdminPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By adminTitle = By.xpath("//h6[text()='Admin']");
    private By usernameField = By.xpath("//input[@placeholder='Username']");
    private By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private By addBtn = By.xpath("//button[normalize-space()='Add']");
    private By noRecords = By.xpath("//span[text()='No Records Found']");

    private By userRoleDropdown = By.xpath("//label[text()='User Role']/../..//div[contains(@class,'oxd-select-text')]");
    private By essOption = By.xpath("//span[text()='ESS']");
    private By employeeField = By.xpath("//input[@placeholder='Type for hints...']");
    private By newUsernameField = By.xpath("//label[text()='Username']/../following::input[1]");
    private By passwordField = By.xpath("//label[text()='Password']/../following::input[1]");
    private By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/../following::input[1]");
    private By saveBtn = By.xpath("//button[normalize-space()='Save']");
    private By successMessage = By.xpath("//div[contains(text(),'Success')]");

    private By checkbox = By.cssSelector(".oxd-table-row input[type='checkbox']");
    private By deleteBtn = By.xpath("//button[contains(@class,'oxd-icon-button--danger')]");
    private By confirmDeleteBtn = By.xpath("//button[normalize-space()='Yes, Delete']");

    public boolean isAdminPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(adminTitle)).isDisplayed();
    }

    public void searchUser(String username) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        field.clear();
        field.sendKeys(username);

        driver.findElement(searchBtn).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".oxd-table-body")));
    }

    public boolean isUserDisplayed(String username) {
        By result = By.xpath("//div[text()='" + username + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(result)).isDisplayed();
    }

    public boolean isNoRecordMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecords)).isDisplayed();
    }

    public void clickAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }

    public void addUser(String employeeName, String username, String password) {

        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        driver.findElement(essOption).click();

        driver.findElement(employeeField).sendKeys(employeeName);
        driver.findElement(newUsernameField).clear();
        driver.findElement(newUsernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);

        driver.findElement(saveBtn).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    public void deleteUser() {
        wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        driver.findElement(deleteBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn)).click();
    }
}
