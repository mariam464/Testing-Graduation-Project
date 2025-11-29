package com.example.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackersTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        driver.get("http://localhost/orangehrm-5.7/web/index.php/auth/login");

        WebElement usernameTF = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordTF = driver.findElement(By.name("password"));
        WebElement loginBTN   = driver.findElement(By.className("orangehrm-login-button"));
        usernameTF.sendKeys("youstina");
        passwordTF.sendKeys("Yoyo112001%");
        loginBTN.click();

        WebElement performanceMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Performance']")
        ));
        performanceMenu.click();

        WebElement configureMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Configure')]")
        ));
        configureMenu.click();

        WebElement trackersOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Trackers']")));
        trackersOption.click();


        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Add')]")));
        addBtn.click();


        String trackerName = "Automation Tracker Test";
        WebElement trackerNameTF = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Tracker Name']/../following-sibling::div/input")
        ));
        trackerNameTF.sendKeys(trackerName);


        WebElement employeeInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Employee']/../following-sibling::div//input")
        ));
        employeeInput.sendKeys("a");
        WebElement firstEmployee = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][1]")));
        firstEmployee.click();


        WebElement reviewerInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Reviewers']/../following-sibling::div//input")
        ));
        reviewerInput.sendKeys("a");
        WebElement firstReviewer = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][1]")));
        firstReviewer.click();


        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Save')]")));
        saveBtn.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'Successfully Saved')]")
        ));


        WebElement searchNameInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Tracker Name']/../following-sibling::div//input")
        ));
        searchNameInput.sendKeys(trackerName);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Search')]")));
        searchBtn.click();


        WebElement resultTrackerName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-card']//div[contains(text(),'" + trackerName + "')]")
        ));

        if (resultTrackerName.isDisplayed()) {
            System.out.println("✔ TRACKER FOUND SUCCESSFULLY: " + trackerName);
        } else {
            System.out.println("✖ TRACKER NOT FOUND!");
        }

        driver.quit();
    }
}
