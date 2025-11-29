package com.example.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeReviewsTest {

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

        WebElement manageReviewsDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Manage Reviews')]")
        ));
        manageReviewsDropdown.click();

        WebElement employeeReviewsOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='Employee Reviews']")
        ));
        employeeReviewsOption.click();


        WebElement employeeInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Employee']/../following-sibling::div//input")
        ));
        employeeInput.sendKeys("a");

        WebElement firstEmployee = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][1]")
        ));
        String selectedEmployee = firstEmployee.getText();
        firstEmployee.click();

        WebElement reviewStatusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Review Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
        ));
        reviewStatusDropdown.click();

        WebElement firstStatusOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][1]")
        ));
        String selectedStatus = firstStatusOption.getText();
        firstStatusOption.click();

        WebElement fromDate = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='From']/../following-sibling::div//input")
        ));
        fromDate.sendKeys("2025-01-01");

        WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='To']/../following-sibling::div//input")
        ));
        toDate.sendKeys("2025-12-31");

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Search')]")
        ));
        searchBtn.click();

        Thread.sleep(1000);


        try {
            WebElement tableResult = driver.findElement(
                    By.xpath("//div[@class='oxd-table-card']")
            );

            System.out.println("Employee reviews found for: " + selectedEmployee
                    + " | Status: " + selectedStatus);

        } catch (Exception e) {

            System.out.println("No reviews found for Employee: " + selectedEmployee
                    + " | Status: " + selectedStatus);

        }

        driver.quit();
    }
}
