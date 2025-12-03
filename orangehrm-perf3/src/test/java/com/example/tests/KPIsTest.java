package com.example.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class KPIsTest {
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

        WebElement kpiOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='KPIs']")
        ));
        kpiOption.click();


        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Add')]")
        ));
        addBtn.click();

        WebElement jobTitleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Job Title']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
        ));
        jobTitleDropdown.click();

        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][1]")
        ));
        String chosenJobTitle = firstOption.getText();
        firstOption.click();

        String kpiName = "Delivery Speed Automation KPI";
        WebElement kpiInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Key Performance Indicator']/../following-sibling::div/input")
        ));
        kpiInput.sendKeys(kpiName);

        WebElement minRate = driver.findElement(
                By.xpath("//label[text()='Minimum Rating']/../following-sibling::div/input"));
        minRate.sendKeys("1");

        WebElement maxRate = driver.findElement(
                By.xpath("//label[text()='Maximum Rating']/../following-sibling::div/input"));
        maxRate.sendKeys("5");

        WebElement saveBtn = driver.findElement(By.xpath("//button[contains(.,'Save')]"));
        saveBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(.,'Successfully Saved')]")
        ));




        WebElement searchJobDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Job Title']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
        ));
        searchJobDropdown.click();

        WebElement searchOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option'][contains(.,'" + chosenJobTitle + "')]")
        ));
        searchOption.click();

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Search')]")
        ));
        searchBtn.click();

        WebElement kpiResult = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table-card']//div[contains(text(),'" + kpiName + "')]")
        ));

        if (kpiResult.isDisplayed()) {
            System.out.println("KPI FOUND SUCCESSFULLY: " + kpiName);
        } else {
            System.out.println("KPI NOT FOUND!");
        }

        driver.quit();
    }
}
