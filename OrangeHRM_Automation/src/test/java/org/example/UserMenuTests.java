package org.example;

import org.example.Pages.UserMenuList;
import org.testng.annotations.Test;

public class UserMenuTests extends SetUp {

    UserMenuList userMenu = new UserMenuList();
    @Test
    public void testAboutSection() {
        userMenu.setup();
        userMenu.clickAbout();
        userMenu.assertAbout();
    }

    @Test
    public void testSupportSection() {
        userMenu.setup();
        userMenu.clickSupport();
        userMenu.assertSupport();
    }

    @Test
    public void testNavigatingToChangePasswordSection() {
        userMenu.setup();
        userMenu.navigateToChangePassword();
        userMenu.assertChangePasswordNavigation();
    }

    @Test
    public void testLogout() {
        userMenu.setup();
        userMenu.clickLogout();
        userMenu.assertLogout();
    }
}
