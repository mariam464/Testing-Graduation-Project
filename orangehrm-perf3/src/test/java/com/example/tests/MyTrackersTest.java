package com.example.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTrackersTest {
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

        WebElement myTrackers = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='My Trackers']")
        ));
        myTrackers.click();

        driver.quit();
    }
}
