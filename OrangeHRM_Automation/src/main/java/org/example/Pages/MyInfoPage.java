package org.example.Pages;

import org.example.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyInfoPage extends SetUp {

    // locators
    private final By personalDetails = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[1]/a");
    private final By contactDetails = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[2]/a");
    private final By emergencyContacts = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[3]/a");
    private final By dependents = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[4]/a");
    private final By immigration = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[5]/a");
    private final By job = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[6]/a");
    private final By salary = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[7]/a");
    private final By report_to = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[8]/a");
    private final By qualification = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[9]/a");
    private final By membership = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[10]/a");

    public MyInfoPage() {
    }

    // actions
    public void setup() {
        new LoginPage().login("mariam29", "Mariam@2982006");
        new Sidebar().goToMyInfo();
    }

    public void goToTab(String tabName) {
        By tabLocator = switch (tabName.toLowerCase()) {
            case "personal details" -> personalDetails;
            case "contact details" -> contactDetails;
            case "emergency contacts" -> emergencyContacts;
            case "dependents" -> dependents;
            case "immigration" -> immigration;
            case "job" -> job;
            case "salary" -> salary;
            case "report-to" -> report_to;
            case "qualifications" -> qualification;
            case "memberships" -> membership;
            default -> throw new IllegalArgumentException("Invalid tab name: " + tabName);
        };
        wait.until(ExpectedConditions.elementToBeClickable(tabLocator)).click();
    }
}