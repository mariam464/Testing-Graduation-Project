package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RecruitmentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- GENERAL LOCATORS (Add Candidate) ---
    private By addButton = By.xpath("//button[contains(.,'Add')]");
    private By firstNameField = By.name("firstName");
    private By middleNameField = By.name("middleName");
    private By lastNameField = By.name("lastName");
    private By emailField = By.xpath("//label[text()='Email']/../following-sibling::div/input");
    private By vacancyDropdown = By.xpath("//label[text()='Vacancy']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]");
    private By resumeUpload = By.xpath("//input[@type='file']");
    private By saveButton = By.xpath("//button[@type='submit']");

    // --- PROFILE BUTTONS ---
    private By shortlistButtonProfile = By.xpath("//button[normalize-space()='Shortlist']");
    private By scheduleInterviewButtonProfile = By.xpath("//button[normalize-space()='Schedule Interview']");
    private By markInterviewPassedButton = By.xpath("//button[normalize-space()='Mark Interview Passed']");

    // --- INTERVIEW MODAL LOCATORS ---
    private By modalSaveButton = By.xpath("//button[@type='submit' and contains(.,'Save')]");
    private By interviewTitleField = By.xpath("//label[text()='Interview Title']/../following-sibling::div//input");
    private By interviewerDropdownInput = By.xpath("//label[text()='Interviewer']/../following-sibling::div//input");
    private By dateField = By.xpath("//label[text()='Date']/../following-sibling::div//input");
    private By timeField = By.xpath("//label[text()='Time']/../following-sibling::div//input");

    // Loading spinner
    private By loadingSpinner = By.xpath("//div[@class='oxd-loading-spinner']");

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------------- Candidate Row View Icon ----------------
    private By viewIcon(String fullName, String vacancyName) {
        return By.xpath(
                "//div[@role='row']" +
                        "[.//div[text()='" + vacancyName + "']]" +
                        "[.//div[text()='" + fullName + "']]" +
                        "//button[contains(@class,'oxd-icon-button')][1]"
        );
    }

    // ---------------------- Candidate Creation ----------------------
    public void clickAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void fillCandidateName(String first, String middle, String last) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(first);
        driver.findElement(middleNameField).sendKeys(middle);
        driver.findElement(lastNameField).sendKeys(last);
    }

    public void selectVacancy(String vacancyName) {
        wait.until(ExpectedConditions.elementToBeClickable(vacancyDropdown)).click();
        By vacancyOption = By.xpath("//span[text()='" + vacancyName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(vacancyOption)).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void uploadResume(String path) {
        driver.findElement(resumeUpload).sendKeys(path);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
    }

    // ---------------------- Back to List ----------------------
    public void navigateBackToRecruitmentList() {
        By recruitmentMenuLink = By.partialLinkText("Recruitment");
        wait.until(ExpectedConditions.elementToBeClickable(recruitmentMenuLink)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(addButton));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
    }

    // ---------------------- View Profile (FIXED) ----------------------
    public void goToCandidateProfile(String fullName, String vacancyName) {

        By viewBtn = viewIcon(fullName, vacancyName);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(viewBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        // Wait for Shortlist or Schedule Interview
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(shortlistButtonProfile),
                ExpectedConditions.presenceOfElementLocated(scheduleInterviewButtonProfile)
        ));
    }

    // ---------------------- SHORTLIST ----------------------
    public void shortlistCandidateFromProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(shortlistButtonProfile)).click();

        WebElement saveModalButton = wait.until(
                ExpectedConditions.elementToBeClickable(modalSaveButton)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveModalButton);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        wait.until(ExpectedConditions.presenceOfElementLocated(scheduleInterviewButtonProfile));
    }

    // ---------------------- SCHEDULE INTERVIEW ----------------------
    public void scheduleInterviewFromProfile(String title, String interviewerName, String date, String time) throws InterruptedException {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));

        // Correct locator for OrangeHRM 5.7
        By scheduleBtn = By.xpath("//button[normalize-space()='Schedule']");

        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(scheduleBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        Thread.sleep(500);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        // Fill modal
        wait.until(ExpectedConditions.visibilityOfElementLocated(interviewTitleField)).sendKeys(title);
        driver.findElement(interviewerDropdownInput).sendKeys(interviewerName);
        Thread.sleep(500);
        driver.findElement(interviewerDropdownInput).sendKeys(Keys.ENTER);

        driver.findElement(dateField).sendKeys(date);
        driver.findElement(timeField).sendKeys(time);

        // Save modal
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(modalSaveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));

        wait.until(ExpectedConditions.presenceOfElementLocated(markInterviewPassedButton));
    }
}


