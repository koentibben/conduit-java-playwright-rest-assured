package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    Page page;

    public BasePage() {
    }

    public void locatorShouldBeVisible(Locator locator) throws Exception {
        if (!locator.isVisible()) {
            throw new Exception(
                    locator + " should BE visible, but IS NOT!"
            );
        }
    }

    public void locatorShouldNotBeVisible(Locator locator) throws Exception {
        if (locator.isVisible()) {
            throw new Exception(
                    locator + " should NOT BE visible, but IS!"
            );
        }
    }

    public void signOutUserIfPossible(HeaderNavigationBar headerNavigationBar, ProfilePage profilePage, ProfileSettingsPage profileSettingsPage) {
        if (headerNavigationBar.getSignedInUserButtonLocator().isVisible()) {
            headerNavigationBar.getSignedInUserButtonLocator().click();
            profilePage.getEditProfileSettingsButton().click();
            profileSettingsPage.getLogOutButton().click();
        }
    }
}
