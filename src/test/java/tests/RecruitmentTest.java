package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RecruitmentPage;
import pages.DashboardPage;
import utilities.DriverFactory;

import static org.openqa.selenium.By.xpath;

public class RecruitmentTest {

    private WebDriver driver;
    private String adminUsername = "maryam55";
    private String adminPassword = "011010012@mA";

    @BeforeClass
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("http://localhost/orangehrm-5.7/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @Test
    public void testFullCandidateHiringFlow() throws InterruptedException {

        // Candidate Data
        String firstName = "Adham";
        String middleName = "Mahmoud";
        String lastName = "salama";
        String fullName = firstName + " " + middleName + " " + lastName;

        String candidateEmail = "candidate." + System.currentTimeMillis() + "@example.com";
        String resumePath = "C:\\Users\\11\\Documents\\Maryam Hamada_CV.pdf";

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(adminUsername, adminPassword);

        RecruitmentPage recPage = dashboardPage.navigateToRecruitment();

        // Add Candidate
        recPage.clickAdd();
        Thread.sleep(2000);
        recPage.fillCandidateName(firstName, middleName, lastName);
        Thread.sleep(2000);
        recPage.selectVacancy("software engineer");
        Thread.sleep(2000);
        recPage.enterEmail(candidateEmail);
        Thread.sleep(2000);
        recPage.uploadResume(resumePath);
        Thread.sleep(2000);
        recPage.clickSave();

        // Back to list
        recPage.navigateBackToRecruitmentList();

        // Open Profile
        recPage.goToCandidateProfile(fullName, "software engineer");

        // Shortlist
        recPage.shortlistCandidateFromProfile();

        // Schedule Interview
        recPage.scheduleInterviewFromProfile(
                "Technical interview",
                "Mazen khaled",
                "2026-10-25",
                "10:00"
        );

        // Validation
        String pageHeader = driver.findElement(xpath("//h6")).getText();
        Assert.assertTrue(pageHeader.contains("Candidate"), " Not on Candidate Profile Page");

        Assert.assertTrue(driver.getPageSource().contains("Mark Interview Passed"),
                " Interview was not scheduled properly.");

        System.out.println(" Full Hiring Flow Completed Successfully!");
    }

    @AfterClass
    public void teardown() {
        DriverFactory.quitDriver();
    }
}
