package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SetUp {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static SoftAssert softAssert;
    protected final String BASE_URL = "http://localhost:8080/orangehrm-5.7/web/index.php/auth/login";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}