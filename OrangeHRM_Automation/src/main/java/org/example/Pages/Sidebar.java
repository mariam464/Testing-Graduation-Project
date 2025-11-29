package org.example.Pages;

import org.example.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Sidebar extends SetUp {

    //Locators
    private By dashboard = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(8) a");
    private By admin = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(1) a");
    private By _PIM = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(2) a");
    private By leave = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(3) a");
    private By time = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(4) a");
    private By recruitment = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(5) a");
    private By myInfo = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(6) a");
    private By performance = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(7) a");
    private By directory = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(9) a");
    private By maintenance = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(10) a");
    private By claim = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(11) a");
    private By buzz = By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(12) a");

    //Action
    public void setUp(){
        new LoginPage().login("mariam29", "Mariam@2982006");
    }

    public void goToDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboard)).click();
    }

    public void goToAdmin() {
        wait.until(ExpectedConditions.elementToBeClickable(admin)).click();
    }

    public void goToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(_PIM)).click();
    }

    public void goToLeave() {
        wait.until(ExpectedConditions.elementToBeClickable(leave)).click();
    }

    public void goToTime() {
        wait.until(ExpectedConditions.elementToBeClickable(time)).click();
    }

    public void goToRecruitment() {
        wait.until(ExpectedConditions.elementToBeClickable(recruitment)).click();
    }

    public void goToMyInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(myInfo)).click();
    }

    public void goToPerformance() {
        wait.until(ExpectedConditions.elementToBeClickable(performance)).click();
    }

    public void goToDirectory() {
        wait.until(ExpectedConditions.elementToBeClickable(directory)).click();
    }

    public void goToMaintenance() {
        wait.until(ExpectedConditions.elementToBeClickable(maintenance)).click();
    }

    public void goToClaim() {
        wait.until(ExpectedConditions.elementToBeClickable(claim)).click();
    }

    public void goToBuzz() {
        wait.until(ExpectedConditions.elementToBeClickable(buzz)).click();
    }
    public void assertSidebar(){
        goToDashboard();
        softAssert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Dashboard not opened");
        goToAdmin();
        softAssert.assertTrue(driver.getCurrentUrl().contains("admin"), "Admin not opened");
        goToPIM();
        softAssert.assertTrue(driver.getCurrentUrl().contains("pim"), "PIM not opened");
        goToLeave();
        softAssert.assertTrue(driver.getCurrentUrl().contains("leave"), "Leave not opened");
        goToTime();
        softAssert.assertTrue(driver.getCurrentUrl().contains("time"), "Time not opened");
        goToRecruitment();
        softAssert.assertTrue(driver.getCurrentUrl().contains("recruitment"), "Recruitment not opened");
        goToMyInfo();
        softAssert.assertTrue(driver.getCurrentUrl().contains("pim/viewPersonalDetails"), "My Info not opened");
        goToPerformance();
        softAssert.assertTrue(driver.getCurrentUrl().contains("performance"), "Performance not opened");
        goToDirectory();
        softAssert.assertTrue(driver.getCurrentUrl().contains("directory"), "Directory not opened");
        goToMaintenance();
        softAssert.assertTrue(driver.getCurrentUrl().contains("maintenance"), "Maintenance not opened");
        driver.navigate().back();
        goToClaim();
        softAssert.assertTrue(driver.getCurrentUrl().contains("claim"), "Claim not opened");
        goToBuzz();
        softAssert.assertTrue(driver.getCurrentUrl().contains("buzz"), "Buzz not opened");
        softAssert.assertAll();
    }
}