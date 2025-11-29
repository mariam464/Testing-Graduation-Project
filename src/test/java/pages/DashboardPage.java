package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;

    // Locator for the Recruitment main menu link on the dashboard
    private By recruitmentLink = By.xpath("//span[text()='Recruitment']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates to the Recruitment page by clicking the menu link.
     * @return The RecruitmentPage object.
     */
    public RecruitmentPage navigateToRecruitment() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Wait until the recruitment link is visible and clickable
        wait.until(ExpectedConditions.elementToBeClickable(recruitmentLink));

        driver.findElement(recruitmentLink).click();

        // The test now lands on the Recruitment page, so we return that object
        return new RecruitmentPage(driver);
    }

    // You can add more methods here, like:
    // public boolean isDashboardHeaderDisplayed() { ... }
}
