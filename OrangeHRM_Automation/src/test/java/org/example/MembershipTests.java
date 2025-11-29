package org.example;

import org.example.Pages.MembershipPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MembershipTests extends SetUp {

    MembershipPage membershipsPage;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void initPage() {
        membershipsPage = new MembershipPage();
    }

    @Test(priority = 1)
    public void testMembershipCRUD() {
        membershipsPage.loginAndNavigateToMemberships();
        String membership = "ACCA";
        String amount = "100.00";
        String paidBy = "Company";
        String currency = "United States Dollar";

        membershipsPage.addMembership(membership, paidBy, amount, currency, "2023-01-01", "2024-01-01");
        membershipsPage.assertMembershipAdded(membership);
        String newAmount = "150.00";
        membershipsPage.updateMembership(membership, newAmount);
        membershipsPage.assertMembershipAdded(membership);
        membershipsPage.clickDelete(membership);
        membershipsPage.assertMembershipDeleted(membership);
    }
}