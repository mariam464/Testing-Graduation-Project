package org.example.Pages;

import org.example.SetUp;
import org.example.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class QualificationsPage extends SetUp {

    private ElementUtils util;
    //locators
    private By spinner = By.className("oxd-form-loader");
    private String addButton= ".orangehrm-action-header button";

    private By saveBtn = By. cssSelector("button[type='submit']");
    private By deleteConfirmBtn = By.className("oxd-button--label-danger");
    //Work experience
    private By company = By.xpath("//label[contains(text(), 'Company')]/following::input[1]");
    private By jobTitle = By.xpath("//label[contains(text(), 'Company')]/following::input[2]");
    private By workFromDate = By.xpath("//label[contains(text(), 'Company')]/following::input[3]");
    private By workToDate = By.xpath("//label[contains(text(), 'Company')]/following::input[4]");
    private By workComment = By.tagName("textarea");

    // education locators
    private By institute = By.xpath("//label[contains(text(), 'Institute')]/following::input[1]");
    private By specialization = By.xpath("//label[contains(text(), 'Major')]/following::input[1]");
    private By year = By.xpath("//label[contains(text(), 'Year')]/following::input[1]");
    private By gpa = By.xpath("//label[contains(text(), 'GPA')]/following::input[1]");
    private By eduStartDate = By.xpath("//label[contains(text(), 'Start Date')]/following::input[1]");
    private By eduEndDate = By.xpath("//label[contains(text(), 'End Date')]/following::input[1]");

    // skills locators
    private By skillDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[4]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
    private By yearsOfExp = By.xpath("//label[contains(text(), 'Years of Experience')]/following::input[1]");
    private By skillComment = By.tagName("textarea");
    //language
    private By languageDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
    private By fluencyDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
    private By competencyDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");
    private By langComment = By.tagName("textarea");
    //license
    private By licenseTypeDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[6]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
    private By licenseNumber = By.xpath("//label[contains(text(), 'License Number')]/following::input[1]");
    private By licenseIssuedDate = By.xpath("//label[contains(text(), 'Issued Date')]/following::input[1]");
    private By licenseExpiryDate = By.xpath("//label[contains(text(), 'Expiry Date')]/following::input[1]");

    public QualificationsPage() {
        if (driver == null) {
            throw new IllegalStateException("Driver is null. Initialize QualificationsPage inside @BeforeMethod.");
        }
        this.util = new ElementUtils(driver);
    }

    private void waitForSpinner() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        } catch (Exception e) {}
    }
    private void addWorkExperience(){
        WebElement addBtn = driver.findElements(By.className(addButton)).get(0);
    }
    private void clickAddButtonForSection(String sectionName) {
        waitForSpinner();
        List<WebElement> addBtns = driver.findElements(By.className(addButton));
        if(sectionName.equals("Work Experience")) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtns.get(0))).click();
        } else if(sectionName.equals("Education")) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtns.get(1))).click();
        } else if(sectionName.equals("Skills")) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtns.get(2))).click();
        } else if(sectionName.equals("Languages")) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtns.get(3))).click();
        } else if(sectionName.equals("License")) {
            wait.until(ExpectedConditions.elementToBeClickable(addBtns.get(4))).click();
        }
        waitForSpinner();
    }

    private void selectDropdownOption(String labelName, String optionText) {
        By dropdown = By.xpath("//label[contains(text(), '" + labelName + "')]/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By option = By.xpath("//div[@role='listbox']//span[text()='" + optionText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    private void clickActionIcon(String sectionName, String uniqueText, String iconClassSuffix) {
        waitForSpinner();
        String xpath = String.format(
                "//h6[normalize-space()='%s']/parent::div/following-sibling::div//div[@role='row' and contains(., '%s')]//button[.//i[contains(@class, '%s')]]",
                sectionName, uniqueText, iconClassSuffix
        );
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public void clickEdit(String sectionName, String uniqueText) {
        clickActionIcon(sectionName, uniqueText, "bi-pencil-fill");
        waitForSpinner();
    }

    public void clickDelete(String sectionName, String uniqueText) {
        clickActionIcon(sectionName, uniqueText, "bi-trash");
        wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmBtn)).click();
        waitForSpinner();
    }

    public void loginAndNavigateToQualifications() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
        waitForSpinner();
        new MyInfoPage().goToTab("Qualifications");
        waitForSpinner();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[text()='Qualifications']")));
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        waitForSpinner();
    }

    public void addWorkExperience(String companyName, String title, String from, String to, String comment) {
        clickAddButtonForSection("Work Experience");
        fillWorkExperience(companyName, title, from, to, comment);
        clickSave();
    }

    public void updateWorkExperience(String oldCompany, String newCompany, String newTitle) {
        clickEdit("Work Experience", oldCompany);
        util.robustClear(company); driver.findElement(company).sendKeys(newCompany);
        util.robustClear(jobTitle); driver.findElement(jobTitle).sendKeys(newTitle);
        clickSave();
    }

    public void deleteWorkExperience(String companyName) {
        clickDelete("Work Experience", companyName);
    }

    private void fillWorkExperience(String companyName, String title, String from, String to, String comment) {
        util.robustClear(company); driver.findElement(company).sendKeys(companyName);
        util.robustClear(jobTitle); driver.findElement(jobTitle).sendKeys(title);
        util.robustClear(workFromDate); driver.findElement(workFromDate).sendKeys(from);
        util.robustClear(workToDate); driver.findElement(workToDate).sendKeys(to);
        util.robustClear(workComment); driver.findElement(workComment).sendKeys(comment);
    }

    public void addEducation(String level, String inst, String major, String yr, String score, String start, String end) {
        clickAddButtonForSection("Education");
        fillEducation(level, inst, major, yr, score, start, end);
        clickSave();
    }

    public void updateEducation(String oldInstitute, String newInstitute, String newGpa) {
        clickEdit("Education", oldInstitute);
        util.robustClear(institute); driver.findElement(institute).sendKeys(newInstitute);
        util.robustClear(gpa); driver.findElement(gpa).sendKeys(newGpa);
        clickSave();
    }

    public void deleteEducation(String instituteName) {
        clickDelete("Education", instituteName);
    }

    private void fillEducation(String level, String inst, String major, String yr, String score, String start, String end) {
        selectDropdownOption("Level", level);
        util.robustClear(institute); driver.findElement(institute).sendKeys(inst);
        util.robustClear(specialization); driver.findElement(specialization).sendKeys(major);
        util.robustClear(year); driver.findElement(year).sendKeys(yr);
        util.robustClear(gpa); driver.findElement(gpa).sendKeys(score);
        if (!start.isEmpty()) { util.robustClear(eduStartDate); driver.findElement(eduStartDate).sendKeys(start); }
        if (!end.isEmpty()) { util.robustClear(eduEndDate); driver.findElement(eduEndDate).sendKeys(end); }
    }

    public void addSkill(String skillName, String years, String comment) {
        clickAddButtonForSection("Skills");
        selectDropdownOption("Skill", skillName);
        if (!years.isEmpty()) { util.robustClear(yearsOfExp); driver.findElement(yearsOfExp).sendKeys(years); }
        if (!comment.isEmpty()) { util.robustClear(skillComment); driver.findElement(skillComment).sendKeys(comment); }
        clickSave();
    }

    public void updateSkill(String skillName, String newYears) {
        clickEdit("Skills", skillName);
        util.robustClear(yearsOfExp); driver.findElement(yearsOfExp).sendKeys(newYears);
        clickSave();
    }

    public void deleteSkill(String skillName) {
        clickDelete("Skills", skillName);
    }

    public void addLanguage(String language, String fluency, String competency, String comment) {
        clickAddButtonForSection("Languages");
        selectDropdownOption("Language", language);
        selectDropdownOption("Fluency", fluency);
        selectDropdownOption("Competency", competency);
        if (!comment.isEmpty()) { util.robustClear(langComment); driver.findElement(langComment).sendKeys(comment); }
        clickSave();
    }

    public void updateLanguage(String language, String newFluency) {
        clickEdit("Languages", language);
        selectDropdownOption("Fluency", newFluency);
        clickSave();
    }

    public void deleteLanguage(String language) {
        clickDelete("Languages", language);
    }

    public void addLicense(String type, String number, String issued, String expiry) {
        clickAddButtonForSection("License");
        selectDropdownOption("License Type", type);
        util.robustClear(licenseNumber); driver.findElement(licenseNumber).sendKeys(number);
        util.robustClear(licenseIssuedDate); driver.findElement(licenseIssuedDate).sendKeys(issued);
        util.robustClear(licenseExpiryDate); driver.findElement(licenseExpiryDate).sendKeys(expiry);
        clickSave();
    }

    public void updateLicense(String type, String newNumber) {
        clickEdit("License", type); // Usually type identifies it, or we use number
        util.robustClear(licenseNumber); driver.findElement(licenseNumber).sendKeys(newNumber);
        clickSave();
    }

    public void deleteLicense(String type) {
        clickDelete("License", type);
    }

    public boolean isRecordDisplayed(String sectionName, String keyText) {
        waitForSpinner();
        String xpath = "//h6[normalize-space()='" + sectionName + "']/parent::div/following-sibling::div//div[contains(text(), '" + keyText + "')]";
        return !driver.findElements(By.xpath(xpath)).isEmpty();
    }

    public void assertRecordAdded(String section, String keyText) {
        Assert.assertTrue(isRecordDisplayed(section, keyText), "Record '" + keyText + "' not found in section '" + section + "'");
    }

    public void assertRecordDeleted(String section, String keyText) {
        Assert.assertFalse(isRecordDisplayed(section, keyText), "Record '" + keyText + "' STILL found in section '" + section + "' after deletion");
    }
}