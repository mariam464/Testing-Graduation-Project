package org.example;

import org.example.Pages.DashboardPage;
import org.testng.annotations.Test;

public class DashboardTests extends SetUp {
    DashboardPage dashboard = new DashboardPage();
    @Test
    public void testDashboardWidgets() {
        dashboard.setup();
        dashboard.assertDashboard();
    }
}
