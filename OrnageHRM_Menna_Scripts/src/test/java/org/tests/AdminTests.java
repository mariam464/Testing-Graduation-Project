package org.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.BaseTest;
import pages.DashboardPage;


public class AdminTests extends BaseTest {

    @Test(priority = 1)
    public void openAdminPage() {
        DashboardPage dashboard = new DashboardPage(driver, wait);
        dashboard.openAdminPage();

        AdminPage admin = new AdminPage(driver, wait);
        Assert.assertTrue(admin.isAdminPageLoaded(), "Admin page NOT loaded!");
    }

    @Test(priority = 2)
    public void searchExistingUser() {
        AdminPage admin = new AdminPage(driver, wait);
        admin.searchUser("Admin");

        Assert.assertTrue(admin.isUserDisplayed("Admin"), "Admin user NOT found!");
    }

    @Test(priority = 3)
    public void searchNonExistingUser() {
        AdminPage admin = new AdminPage(driver, wait);
        admin.searchUser("FakeUserXYZ");

        Assert.assertTrue(admin.isNoRecordMessage(), "Expected 'No Records Found'!");
    }

    @Test(priority = 4)
    public void addNewUser() {
        AdminPage admin = new AdminPage(driver, wait);
        admin.clickAddUser();

        admin.addUser("Paul", "TestUser5678", "Test@1234");

        Assert.assertTrue(admin.isSuccessMessageDisplayed(), "User NOT added!");
    }

    @Test(priority = 5)
    public void deleteUser() {
        AdminPage admin = new AdminPage(driver, wait);

        admin.searchUser("TestUser5678");
        admin.deleteUser();

        Assert.assertTrue(admin.isSuccessMessageDisplayed(), "User NOT deleted!");
    }
}
