package org.example.Pages;

import org.example.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class UserMenuList extends SetUp {
    //locators
    private By user_dropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By about_link = By.cssSelector(".oxd-userdropdown .oxd-dropdown-menu li:nth-of-type(1) .oxd-userdropdown-link");
    private By support_link = By.cssSelector(".oxd-userdropdown .oxd-dropdown-menu li:nth-of-type(2)");
    private By changePasswordLink = By.cssSelector(".oxd-userdropdown .oxd-dropdown-menu li:nth-of-type(3)");
    private By logoutLink = By.cssSelector(".oxd-userdropdown .oxd-dropdown-menu li:nth-of-type(4) .oxd-userdropdown-link");

    public By aboutHeader = By.cssSelector(".orangehrm-main-title");
    public By supportHeader = By.className("orangehrm-main-title");

    public UserMenuList(){
    }

    //actions
    public void setup(){
        new LoginPage().login("mariam29", "Mariam@2982006");
    }

    private void openUserMenu() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".oxd-dialog-container-default")));
        wait.until(ExpectedConditions.elementToBeClickable(user_dropdown)).click();
    }

    public void clickAbout() {
        openUserMenu();
        wait.until(ExpectedConditions.elementToBeClickable(about_link)).click();
    }

    public void clickSupport() {
        openUserMenu();
        wait.until(ExpectedConditions.elementToBeClickable(support_link)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(supportHeader));
    }

    public void navigateToChangePassword() {
        openUserMenu();
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink)).click();
    }

    public void clickLogout() {
        openUserMenu();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
    //assertions
    public void assertAbout(){
        Assert.assertTrue(driver.findElement(aboutHeader).getText().equals("About"),
                "About modal not displayed!");
    }

    public void assertSupport() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(supportHeader));
        Assert.assertTrue(driver.findElement(supportHeader).getText().length() > 0,
                "Support page not opened!");
    }
    public void assertChangePasswordNavigation(){
        Assert.assertEquals(driver.getCurrentUrl(),
                "http://localhost:8080/orangehrm-5.7/web/index.php/pim/updatePassword",
                "Did not navigate to Change Password page!");
    }
    public void assertLogout(){
        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/orangehrm-5.7/web/index.php/auth/login"));
    }
}