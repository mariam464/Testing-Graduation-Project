package org.example.Pages;

import org.example.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class DashboardPage extends SetUp {

    private By dashboardHeader = By.cssSelector("h6:nth-of-type(1)");

    private By timeAtWork = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p");
    private By myActions = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div/p");
    private By quick_launch = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[1]/div/p");

    private By assign_leave = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[1]");
    private By leaveList = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[2]");
    private By timesheets = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[3]");
    private By applyLeave = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[4]");
    private By myLeave = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[5]");
    private By myTimesheet = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[6]");

    private By buzzPosts = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[5]/div/div[1]");

    private By emp_on_leave = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[5]/div/div[1]");
    private By subUnitChart = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[6]/div/div[1]");

    private By locationChart = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[7]/div/div[1]");

    // Actions
    public void setup(){
        new LoginPage().login("mariam29", "Mariam@2982006");
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTimeAtWorkDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(timeAtWork)).isDisplayed();
    }

    public boolean isMyActionsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(myActions)).isDisplayed();
    }

    public boolean isQuickLaunchDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quick_launch)).isDisplayed();
    }

    public boolean isAssignLeaveVisible() {
        return driver.findElement(assign_leave).isDisplayed();
    }

    public boolean isLeaveListVisible() {
        return driver.findElement(leaveList).isDisplayed();
    }

    public boolean isTimesheetsVisible() {
        return driver.findElement(timesheets).isDisplayed();
    }

    public boolean isApplyLeaveVisible() {
        return driver.findElement(applyLeave).isDisplayed();
    }

    public boolean isMyLeaveVisible() {
        return driver.findElement(myLeave).isDisplayed();
    }

    public boolean isMyTimesheetVisible() {
        return driver.findElement(myTimesheet).isDisplayed();
    }

    public List<WebElement> getBuzzPosts() {
        return driver.findElements(buzzPosts);
    }

    public List<WebElement> getEmployeesOnLeave() {
        return driver.findElements(emp_on_leave);
    }

    public boolean isSubUnitChartDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(subUnitChart)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLocationChartDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locationChart)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    //assertion
    public void assertDashboard(){
        softAssert.assertTrue(isTimeAtWorkDisplayed(), "Time at Work widget missing");
        softAssert.assertTrue(isMyActionsDisplayed(), "My Actions widget missing");
        softAssert.assertTrue(isQuickLaunchDisplayed(), "Quick Launch widget missing");
        softAssert.assertTrue(isAssignLeaveVisible(), "Assign Leave button missing");
        softAssert.assertTrue(isLeaveListVisible(), "Leave List button missing");
        softAssert.assertTrue(isTimesheetsVisible(), "Timesheets button missing");
        softAssert.assertTrue(isApplyLeaveVisible(), "Apply Leave button missing");
        softAssert.assertTrue(isMyLeaveVisible(), "My Leave button missing");
        softAssert.assertTrue(isMyTimesheetVisible(), "My Timesheet button missing");
        softAssert.assertTrue(!getBuzzPosts().isEmpty(), "No Buzz posts found (or list is empty)");
        softAssert.assertTrue(!getEmployeesOnLeave().isEmpty(), "No employees on leave found (or list is empty)");
        softAssert.assertTrue(isSubUnitChartDisplayed(), "Employee Distribution by Sub Unit chart is missing");
        softAssert.assertTrue(isLocationChartDisplayed(), "Employee Distribution by Location chart is missing");
        softAssert.assertAll();
    }
}