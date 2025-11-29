package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    // ... Locators remain the same ...
    private By usernameField = By.xpath("//input[@placeholder='Username']");
    private By passwordField = By.xpath("//input[@placeholder='Password']");
    private By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public DashboardPage login(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Set to 60s for stability

        // 1. Wait for and Enter Username
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameField));
        driver.findElement(usernameField).sendKeys(username);

        // 2. Wait for and Enter Password
        driver.findElement(passwordField).sendKeys(password);

        // 3. Click Login Button
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();


        // 4. Define a unique element on the Dashboard page
        By dashboardUniqueElement = By.xpath("//span[text()='PIM']");

        // 5. Wait until the Dashboard element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardUniqueElement));

        // 6. NOW it's safe to return the DashboardPage object
        return new DashboardPage(driver);
    }
}