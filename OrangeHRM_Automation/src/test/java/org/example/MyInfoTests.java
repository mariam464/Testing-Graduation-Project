package org.example;

import org.example.Pages.MyInfoPage;
import org.testng.annotations.Test;

public class MyInfoTests extends SetUp {
    MyInfoPage myInfo = new MyInfoPage();
    @Test
    public void testNavigateMyInfoTabs() {
        myInfo.setup();
        myInfo.goToTab("Contact Details");
        myInfo.goToTab("Emergency Contacts");
        myInfo.goToTab("Dependents");
        myInfo.goToTab("Immigration");
        myInfo.goToTab("Job");
        myInfo.goToTab("Salary");
        myInfo.goToTab("Report-to");
        myInfo.goToTab("Qualifications");
        myInfo.goToTab("Memberships");
        myInfo.goToTab("Personal Details");
    }
}
