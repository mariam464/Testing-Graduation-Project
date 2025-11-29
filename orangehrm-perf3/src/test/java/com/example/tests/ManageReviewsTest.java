package com.example.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageReviewsTest {
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
                By.xpath("//span[text()='Performance']")));
        performanceMenu.click();

        WebElement manageReviewsTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Manage Reviews']")));
        manageReviewsTab.click();



        WebElement jobTitleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Job Title']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
        ));
        jobTitleDropdown.click();

        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][1]")
        ));
        String chosenJob = firstOption.getText();
        firstOption.click();

        WebElement fromDate = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='From Date']/../following-sibling::div//input")
        ));
        fromDate.sendKeys("2025-01-01");

        WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='To Date']/../following-sibling::div//input")
        ));
        toDate.sendKeys("2025-12-31");

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Search')]")
        ));
        searchBtn.click();


        try {
            WebElement resultsTable = driver.findElement(
                    By.xpath("//div[@class='oxd-table-card']")
            );

            System.out.println("Records Found for Job Title: " + chosenJob);

        } catch (Exception e) {
            System.out.println("No Records Found for Job Title: " + chosenJob);
        }

        driver.quit();
    }
}
