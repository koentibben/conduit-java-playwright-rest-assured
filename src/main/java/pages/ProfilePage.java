package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProfilePage extends BasePage {
    Page page;
    Locator editProfileSettingsButton;

    public ProfilePage(Page page) {
        this.page = page;
        this.editProfileSettingsButton = page.locator("//a[contains(@href, '/settings')]");
    }

    public Locator getEditProfileSettingsButton() {
        return editProfileSettingsButton;
    }
}
