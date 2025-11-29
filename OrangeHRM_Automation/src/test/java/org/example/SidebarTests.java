package org.example;

import org.example.Pages.Sidebar;
import org.testng.annotations.Test;

public class SidebarTests extends SetUp {
    Sidebar sidebar = new Sidebar();
    @Test
    public void testSidebarNavigation() {
        sidebar.setUp();
        sidebar.assertSidebar();
    }

}
